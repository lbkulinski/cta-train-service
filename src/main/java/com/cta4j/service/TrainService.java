package com.cta4j.service;

import com.cta4j.client.StationClient;
import com.cta4j.client.TrainClient;
import com.cta4j.exception.DataFetcherException;
import com.cta4j.model.Station;
import com.cta4j.model.Train;
import com.cta4j.model.TrainBody;
import com.cta4j.model.TrainResponse;
import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public final class TrainService {
    private final StationClient stationClient;

    private final TrainClient trainClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(TrainService.class);
    }

    @Autowired
    public TrainService(StationClient stationClient, TrainClient trainClient, Rollbar rollbar) {
        Objects.requireNonNull(stationClient);

        Objects.requireNonNull(trainClient);

        Objects.requireNonNull(rollbar);

        this.stationClient = stationClient;

        this.trainClient = trainClient;

        this.rollbar = rollbar;
    }

    public Set<Station> getStations() {
        Set<Station> stations;

        try {
            stations = this.stationClient.getStations();
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            TrainService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        if (stations == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        return stations;
    }

    public Set<Train> getTrains(int stationId) {
        if (stationId <= 0) {
            String message = "The specified station ID must be positive";

            throw new DataFetcherException(message, ErrorType.BAD_REQUEST);
        }

        TrainResponse response;

        try {
            response = this.trainClient.getTrains(stationId);
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            TrainService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        if (response == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        TrainBody body = response.body();

        if (body == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        Set<Train> trains = body.trains();

        if (trains == null) {
            String message = "Trains with the specified station ID could not be found";

            throw new DataFetcherException(message, ErrorType.NOT_FOUND);
        }

        return trains;
    }
}

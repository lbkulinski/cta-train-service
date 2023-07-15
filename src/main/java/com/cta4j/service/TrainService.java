package com.cta4j.service;

import com.cta4j.client.TrainClient;
import com.cta4j.exception.DataFetcherException;
import com.cta4j.jooq.Tables;
import com.cta4j.model.Station;
import com.cta4j.model.Train;
import com.cta4j.model.TrainBody;
import com.cta4j.model.TrainResponse;
import com.rollbar.notifier.Rollbar;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public final class TrainService {
    private final DSLContext context;

    private final TrainClient trainClient;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(TrainService.class);
    }

    @Autowired
    public TrainService(DSLContext context, TrainClient trainClient, Rollbar rollbar) {
        Objects.requireNonNull(context);

        Objects.requireNonNull(trainClient);

        Objects.requireNonNull(rollbar);

        this.context = context;

        this.trainClient = trainClient;

        this.rollbar = rollbar;
    }

    public Set<Station> getStations() {
        List<Station> stations;

        try {
            stations = this.context.select(Tables.STATION.ID, Tables.STATION.NAME)
                                   .from(Tables.STATION)
                                   .fetchInto(Station.class);
        } catch (DataAccessException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            TrainService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        return Set.copyOf(stations);
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

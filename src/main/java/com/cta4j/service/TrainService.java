package com.cta4j.service;

import com.cta4j.exception.DataFetcherException;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import java.util.Set;

import com.cta4j.model.Train;
import com.cta4j.model.TrainResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import com.cta4j.model.TrainBody;

@Service
public final class TrainService {
    private final TrainClient client;

    @Autowired
    public TrainService(TrainClient client) {
        Objects.requireNonNull(client);

        this.client = client;
    }

    public Set<Train> getTrains(int stationId) {
        if (stationId <= 0) {
            String message = "The specified station ID must be positive";

            throw new DataFetcherException(message, ErrorType.BAD_REQUEST);
        }

        ResponseEntity<TrainResponse> responseEntity = this.client.getTrains(stationId);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if ((statusCode != HttpStatus.OK) || !responseEntity.hasBody()) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        TrainResponse response = responseEntity.getBody();

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
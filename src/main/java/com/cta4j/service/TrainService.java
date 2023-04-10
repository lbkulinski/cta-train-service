package com.cta4j.service;

import com.cta4j.exception.TrainException;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import java.util.List;
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

    public List<Train> readTrains(int stationId) {
        if (stationId <= 0) {
            String message = "The specified station ID must be positive";

            throw new TrainException(message, ErrorType.BAD_REQUEST);
        }

        ResponseEntity<TrainResponse> responseEntity = this.client.getTrains(stationId);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if ((statusCode != HttpStatus.OK) || !responseEntity.hasBody()) {
            String message = "The response from the CTA's train API is malformed";

            throw new TrainException(message, ErrorType.INTERNAL_ERROR);
        }

        TrainResponse response = responseEntity.getBody();

        if (response == null) {
            String message = "The response from the CTA's train API is malformed";

            throw new TrainException(message, ErrorType.INTERNAL_ERROR);
        }

        TrainBody body = response.body();

        if (body == null) {
            String message = "The response from the CTA's train API is malformed";

            throw new TrainException(message, ErrorType.INTERNAL_ERROR);
        }

        List<Train> trains = body.trains();

        if (trains == null) {
            String message = "The response from the CTA's train API is malformed";

            throw new TrainException(message, ErrorType.INTERNAL_ERROR);
        }

        return trains;
    }
}

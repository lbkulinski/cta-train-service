package com.cta4j.service;

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

    public ResponseEntity<List<Train>> readTrains(int mapId) {
        if (mapId <= 0) {
            return ResponseEntity.badRequest()
                                 .build();
        }

        ResponseEntity<TrainResponse> responseEntity = this.client.getTrains(mapId);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if ((statusCode != HttpStatus.OK) || !responseEntity.hasBody()) {
            return ResponseEntity.internalServerError()
                                 .build();
        }

        TrainResponse response = responseEntity.getBody();

        if (response == null) {
            return ResponseEntity.internalServerError()
                                 .build();
        }

        TrainBody body = response.body();

        if (body == null) {
            return ResponseEntity.internalServerError()
                                 .build();
        }

        List<Train> trains = body.trains();

        if (trains == null) {
            trains = List.of();
        }

        return ResponseEntity.ok(trains);
    }
}

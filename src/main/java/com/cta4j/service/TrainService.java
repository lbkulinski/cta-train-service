package com.cta4j.service;

import org.springframework.stereotype.Service;
import com.cta4j.client.StationClient;
import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import java.util.Set;
import com.cta4j.model.Station;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import com.cta4j.model.Train;
import com.cta4j.model.TrainResponse;
import com.cta4j.model.TrainBody;

@Service
public final class TrainService {
    private final StationClient stationClient;

    private final TrainClient trainClient;

    @Autowired
    public TrainService(StationClient stationClient, TrainClient trainClient) {
        Objects.requireNonNull(stationClient);

        Objects.requireNonNull(trainClient);

        this.stationClient = stationClient;

        this.trainClient = trainClient;
    }

    public ResponseEntity<Set<Station>> readStations() {
        ResponseEntity<Set<Station>> responseEntity = this.stationClient.getStations();

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if ((statusCode != HttpStatus.OK) || !responseEntity.hasBody()) {
            return ResponseEntity.internalServerError()
                                 .build();
        }

        Set<Station> stations = responseEntity.getBody();

        if (stations == null) {
            return ResponseEntity.internalServerError()
                                 .build();
        }

        return ResponseEntity.ok(stations);
    }

    public ResponseEntity<Set<Train>> readTrains(int stationId) {
        if (stationId <= 0) {
            return ResponseEntity.badRequest()
                                 .build();
        }

        ResponseEntity<TrainResponse> responseEntity = this.trainClient.getTrains(stationId);

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

        Set<Train> trains = body.trains();

        if (trains == null) {
            trains = Set.of();
        }

        return ResponseEntity.ok(trains);
    }
}

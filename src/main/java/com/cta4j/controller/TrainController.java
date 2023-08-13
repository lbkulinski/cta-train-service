package com.cta4j.controller;

import org.springframework.stereotype.Controller;
import com.cta4j.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import java.util.Set;
import com.cta4j.model.Station;
import com.cta4j.model.Train;
import org.springframework.graphql.data.method.annotation.Argument;

@Controller
public final class TrainController {
    private final TrainService service;

    @Autowired
    public TrainController(TrainService service) {
        Objects.requireNonNull(service);

        this.service = service;
    }

    @QueryMapping
    public Set<Station> getStations() {
        return this.service.getStations();
    }

    @QueryMapping
    public Set<Train> getTrains(@Argument int stationId) {
        return this.service.getTrains(stationId);
    }

    @QueryMapping
    public Set<Train> followTrain(@Argument int run) {
        return this.service.followTrain(run);
    }
}

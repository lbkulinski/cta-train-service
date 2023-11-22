package com.cta4j.controller;

import com.cta4j.model.Station;
import com.cta4j.model.Train;
import com.cta4j.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.Set;

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

    @SubscriptionMapping
    public Flux<Set<Train>> subscribeToTrains(@Argument int stationId) {
        return this.service.subscribeToTrains(stationId);
    }
}

package com.cta4j.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cta4j.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.cta4j.model.Train;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/stations")
public final class TrainController {
    private final TrainService service;

    @Autowired
    public TrainController(TrainService service) {
        Objects.requireNonNull(service);

        this.service = service;
    }

    @GetMapping("/{stationId}/trains")
    public ResponseEntity<List<Train>> readTrains(@PathVariable int stationId) {
        return this.service.readTrains(stationId);
    }
}

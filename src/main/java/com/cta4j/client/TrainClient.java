package com.cta4j.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.http.ResponseEntity;
import com.cta4j.model.TrainResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface TrainClient {
    @GetExchange
    ResponseEntity<TrainResponse> getTrains(@RequestParam("mapid") int stationId);
}

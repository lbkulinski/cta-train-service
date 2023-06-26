package com.cta4j.client;

import com.cta4j.model.TrainResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface TrainClient {
    @GetExchange
    TrainResponse getTrains(@RequestParam("mapid") int stationId);
}

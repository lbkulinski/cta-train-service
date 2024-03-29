package com.cta4j.client;

import com.cta4j.model.FollowResponse;
import com.cta4j.model.TrainResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface TrainClient {
    @GetExchange("/ttarrivals.aspx")
    TrainResponse getTrains(@RequestParam("mapid") int stationId);

    @GetExchange("/ttfollow.aspx")
    FollowResponse followTrain(@RequestParam("runnumber") int run);
}

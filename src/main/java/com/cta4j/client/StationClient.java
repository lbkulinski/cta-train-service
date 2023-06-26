package com.cta4j.client;

import com.cta4j.model.Station;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Set;

public interface StationClient {
    @GetExchange
    Set<Station> getStations();
}

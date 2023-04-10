package com.cta4j.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.http.ResponseEntity;
import java.util.Set;
import com.cta4j.model.Station;

public interface StationClient {
    @GetExchange
    ResponseEntity<Set<Station>> getStations();
}

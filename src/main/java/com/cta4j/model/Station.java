package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Station(
    @JsonAlias("stop_id")
    int id,

    @JsonAlias("station_descriptive_name")
    String name
) {
}

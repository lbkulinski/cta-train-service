package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
@JsonIgnoreProperties(ignoreUnknown = true)
public record Station(
    @JsonAlias("map_id")
    int id,

    @JsonAlias("station_descriptive_name")
    String name
) {
    public Station {
        Objects.requireNonNull(name);
    }
}

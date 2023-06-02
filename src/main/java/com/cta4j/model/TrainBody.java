package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;
import java.util.Set;

@NullMarked
@JsonIgnoreProperties(ignoreUnknown = true)
public record TrainBody(
    @JsonAlias("eta")
    Set<Train> trains
) {
    public TrainBody {
        Objects.requireNonNull(trains);

        trains.forEach(Objects::requireNonNull);
    }
}

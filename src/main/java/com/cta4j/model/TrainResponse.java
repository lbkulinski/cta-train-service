package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record TrainResponse(@JsonAlias("ctatt") TrainBody body) {
    public TrainResponse {
        Objects.requireNonNull(body);
    }
}

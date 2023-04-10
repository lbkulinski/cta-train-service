package com.cta4j.model;

import com.cta4j.serialization.StringToInstantConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.Instant;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.cta4j.serialization.StringToBooleanConverter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Train(
    @JsonAlias("rn")
    int run,

    @JsonAlias("rt")
    Route route,

    @JsonAlias("destNm")
    String destination,

    @JsonAlias("staNm")
    String station,

    @JsonAlias("prdt")
    @JsonDeserialize(converter = StringToInstantConverter.class)
    Instant predictionTime,

    @JsonAlias("arrT")
    @JsonDeserialize(converter = StringToInstantConverter.class)
    Instant arrivalTime,

    @JsonAlias("isApp")
    @JsonDeserialize(converter = StringToBooleanConverter.class)
    boolean due,

    @JsonAlias("isSch")
    @JsonDeserialize(converter = StringToBooleanConverter.class)
    boolean scheduled,

    @JsonAlias("isDly")
    @JsonDeserialize(converter = StringToBooleanConverter.class)
    boolean delayed
) {
}

package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TrainBody(@JsonAlias("eta") Set<Train> trains) {
}

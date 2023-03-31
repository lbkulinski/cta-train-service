package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TrainResponse(@JsonAlias("ctatt") TrainBody body) {
}

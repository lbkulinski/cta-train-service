package com.cta4j.exception;

import org.springframework.graphql.execution.ErrorType;

import java.util.Objects;

public class TrainException extends RuntimeException {
    private final ErrorType errorType;

    public TrainException(String message, ErrorType errorType) {
        super(message);

        Objects.requireNonNull(errorType);

        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}

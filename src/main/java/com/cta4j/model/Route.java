package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;

public enum Route {
    RED,

    BLUE,

    BROWN,

    GREEN,

    ORANGE,

    PURPLE,

    PINK,

    YELLOW;

    @JsonCreator
    public static Route parseString(String string) {
        Objects.requireNonNull(string);

        string = string.toUpperCase();

        return switch (string) {
            case "RED" -> Route.RED;
            case "BLUE" -> Route.BLUE;
            case "BRN" -> Route.BROWN;
            case "G" -> Route.GREEN;
            case "ORG" -> Route.ORANGE;
            case "P" -> Route.PURPLE;
            case "PINK" -> Route.PINK;
            case "Y" -> Route.YELLOW;
            default -> {
                String message = "A route with the name \"%s\" does not exist".formatted(string);

                throw new IllegalArgumentException(message);
            }
        };
    }
}

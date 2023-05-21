package com.cta4j.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;

public enum Line {
    RED,

    BLUE,

    BROWN,

    GREEN,

    ORANGE,

    PURPLE,

    PINK,

    YELLOW;

    @JsonCreator
    public static Line parseString(String string) {
        Objects.requireNonNull(string);

        string = string.toUpperCase();

        return switch (string) {
            case "RED" -> Line.RED;
            case "BLUE" -> Line.BLUE;
            case "BRN" -> Line.BROWN;
            case "G" -> Line.GREEN;
            case "ORG" -> Line.ORANGE;
            case "P" -> Line.PURPLE;
            case "PINK" -> Line.PINK;
            case "Y" -> Line.YELLOW;
            default -> {
                String message = "A line with the name \"%s\" does not exist".formatted(string);

                throw new IllegalArgumentException(message);
            }
        };
    }
}

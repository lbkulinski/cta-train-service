package com.cta4j.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public final class StringToBooleanConverter extends StdConverter<String, Boolean> {
    @Override
    public Boolean convert(String string) {
        return Objects.equals(string, "1");
    }
}

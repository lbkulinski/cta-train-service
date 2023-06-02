package com.cta4j.serialization;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.jspecify.annotations.NullMarked;

import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;

@NullMarked
public final class StringToInstantConverter extends StdConverter<String, Instant> {
    @Override
    public Instant convert(String string) {
        ZoneId chicagoId = ZoneId.of("America/Chicago");

        return LocalDateTime.parse(string)
                            .atZone(chicagoId)
                            .toInstant();
    }
}

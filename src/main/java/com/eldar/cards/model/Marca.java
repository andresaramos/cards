package com.eldar.cards.model;

import com.eldar.cards.exceptions.UnknownMarcaException;
import com.eldar.cards.utils.ObjectUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public enum Marca implements RateMethodCalculated {

    VISA {
        @Override
        public Function<Date, Double> getRateAlgorithm() {
            return date -> Optional.ofNullable(date)
                                        .map(this::getMapOfDate)
                                        .flatMap(params -> Optional.of(params)
                                                                    .map(p -> p.get("year"))
                                                                    .map(ObjectUtils::toDouble)
                                                                    .flatMap(year -> Optional.ofNullable(params.get("month"))
                                                                                             .map(ObjectUtils::toDouble)
                                                                                             .map(month -> year / month)))
                                        .orElse(0.0);
        }
    },

    NARA{
        @Override
        public Function<Date, Double> getRateAlgorithm() {
            return date -> Optional.ofNullable(date)
                    .map(this::getMapOfDate)
                    .map(p -> p.get("day"))
                    .map(ObjectUtils::toDouble)
                    .map(day -> day * 0.5)
                    .orElse(0.0);
        }
    },

    AMEX{
        @Override
        public Function<Date, Double> getRateAlgorithm() {
            return date -> Optional.ofNullable(date)
                    .map(this::getMapOfDate)
                    .map(p -> p.get("day"))
                    .map(ObjectUtils::toDouble)
                    .map(day -> day * 0.1)
                    .orElse(0.0);
        }
    };

    public static Marca of(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new UnknownMarcaException(value));
    }
}

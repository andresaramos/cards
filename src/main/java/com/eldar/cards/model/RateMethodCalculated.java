package com.eldar.cards.model;

import com.eldar.cards.exceptions.InvalidDateFormatException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface RateMethodCalculated {

    Function<Date, Double> getRateAlgorithm();

    default Map<String, Integer> getMapOfDate(Date date) {
        return Optional.ofNullable(date)
                      .map(d -> {
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(d);
                                    return c;
                                })
                .map(c -> Map.of("day", c.get(Calendar.DAY_OF_MONTH), "month", c.get(Calendar.MONTH), "year", c.get(Calendar.YEAR)))
                .orElseThrow(InvalidDateFormatException::new);

    }
}

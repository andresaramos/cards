package com.eldar.cards.utils;

import com.eldar.cards.exceptions.InvalidDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateUtils {

    private DateUtils() {}
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static Date toDate(String strDate) {
        return Optional.ofNullable(strDate)
                .map(sDate -> {
                    try {
                        return format.parse(sDate);
                    } catch (ParseException e) {
                        throw new InvalidDateFormatException(e);
                    }
                })
                .orElseThrow(InvalidDateFormatException::new);

    }

    public static String toString(Date date) {
        return Optional.ofNullable(date)
                .map(format::format)
                .orElseThrow(InvalidDateFormatException::new);
    }

    public static Date actualDate() {
        return new Date();
    }
}

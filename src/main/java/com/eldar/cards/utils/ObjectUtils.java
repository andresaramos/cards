package com.eldar.cards.utils;

import java.util.Optional;
import java.util.function.Function;

public class ObjectUtils {

    private ObjectUtils() {}

    public static <T, L> L convert(T value, Function<T, L> convertFunction) {
        return convertFunction.apply(value);
    }

    public static String toString(Object value) {
        return convert(value, v -> Optional.ofNullable(v)
                                            .map(Object::toString)
                                            .orElseThrow(() -> new RuntimeException("Unexpected value")));
    }

    public static Double toDouble(Object value) {
        return convert(ObjectUtils.toString(value), str -> Optional.of(str)
                                            .map(Double::valueOf)
                                            .orElseThrow(() -> new RuntimeException("Unexpected value")));
    }
}

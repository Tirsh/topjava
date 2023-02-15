package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<? super T>> boolean isBetween(T t, T t1, T t2) {
        return (t1 == null || t.compareTo(t1) >= 0) &&
                (t2 == null || t.compareTo(t2) <= 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(String s) {
        return (s == null || s.isEmpty()) ? null : LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
    }

    public static LocalTime toLocalTime(String s) {
        return (s == null || s.isEmpty()) ? null : LocalTime.parse(s, DateTimeFormatter.ISO_TIME);
    }
}


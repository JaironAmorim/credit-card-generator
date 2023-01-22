package com.jaironamorim.creditcardgenerator.util;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtil {

    public static LocalDate randonGenerateDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}

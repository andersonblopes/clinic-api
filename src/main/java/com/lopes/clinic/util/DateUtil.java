package com.lopes.clinic.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Date util.
 */
@Component
public class DateUtil {

    /**
     * Format local date time to database style string.
     *
     * @param localDateTime the local date time
     * @return the string
     */
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-DD-dd HH:mm:ss").format(localDateTime);
    }
}

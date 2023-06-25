package fr.iut.uca.v1.extension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public abstract class DateExtensions {

    private static final ZoneId zoneId = ZoneId.systemDefault();

    private static final ZoneOffset zoneOffset = ZoneOffset.UTC;

    private DateExtensions() { }

    /**
     * Converts a date to LocalDate.
     * @param date Date
     * @return LocalDate from date
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts a timestamp to LocalDate.
     * The timestamp needs to be in milliseconds.
     * @param timestamp Timestamp in milliseconds
     * @return LocalDate from timestamp
     */
    public static LocalDate toLocalDate(long timestamp) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }

    /**
     * Converts a LocalDate to a timestamp.
     * @param date LocalDate
     * @return long Timestamp in milliseconds
     */
    public static long toTimestamp(LocalDate date) {
        return date.atStartOfDay(zoneOffset).toInstant().toEpochMilli();
    }
}

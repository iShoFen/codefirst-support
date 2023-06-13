package fr.iut.uca.extension;

import java.time.LocalDate;
import java.util.Date;

public abstract class DateExtensions {
    private DateExtensions() { }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

}

package com.hpe.ps.prototype.homeoffice.common;
/* vim:set ft=java et sw=2 ts=2 colorcolumn=80: */

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to return current UTC time as a format.
 */
public class Time {
    /**
     *
     * @return sample: 2021-10-08T21:09:55.824433Z
     */
    public static String getTime() {
        return ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT );
    }
}

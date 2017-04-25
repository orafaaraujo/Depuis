package com.orafaaraujo.depuis.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class responsible to formatted Dates.
 *
 * Created by rafael on 21/01/17.
 */

public class DateTimeHelper {

    private static final String DAY_DATE = "EEE, dd MMM yy";
    private static final String DATE = "dd MMM yy";
    private static final String TIME = "HH:mm";

    /**
     * Format date to day of week, day of month, month and year. For example "Sub, 29 mar 87".
     *
     * @param timestamp the time in milliseconds.
     * @return Return a string formatted by the milliseconds in parameter.
     */
    public String getDayDate(final long timestamp) {
        return doFormat(DAY_DATE, timestamp);
    }

    /**
     * Format date to day, month and year. For example "29 mar 87".
     *
     * @param timestamp the time in milliseconds.
     * @return Return a string formatted by the milliseconds in parameter.
     */
    public String getDate(final long timestamp) {
        return doFormat(DATE, timestamp);
    }

    /**
     * Format time to hour and minutes. For example "09:15".
     *
     * @param timestamp the time in milliseconds.
     * @return Return a string formatted by the milliseconds in parameter.
     */
    public String getTime(final long timestamp) {
        return doFormat(TIME, timestamp);
    }

    private String doFormat(final String pattern, final long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }


    /**
     * Return a date and time in milliseconds of the current moment, but not considering hours,
     * minutes, seconds and milliseconds.
     *
     * @return Time in milliseconds of the current day, without considering time (time is all zeros)
     */
    public long getCleanTime() {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }


}

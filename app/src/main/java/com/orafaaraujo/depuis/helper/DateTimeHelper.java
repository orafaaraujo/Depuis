package com.orafaaraujo.depuis.helper;

import com.orafaaraujo.depuis.dagger.Injector;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rafael on 21/01/17.
 */

public class DateTimeHelper {

    private static final String DAY_DATE = "EEE, dd MMM yy";
    private static final String DATE = "dd MMM yy";
    private static final String TIME = "HH:mm";

    public DateTimeHelper() {
        Injector.getApplicationComponent().inject(this);
    }

    public String getDayDate(final long timestamp) {
        return doFormat(DAY_DATE, timestamp);
    }

    public String getDate(final long timestamp) {
        return doFormat(DATE, timestamp);
    }

    public String getTime(final long timestamp) {
        return doFormat(TIME, timestamp);
    }

    private String doFormat(final String pattern, final long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public long getCleanTime(Calendar cal) {

        Calendar calCopy = (Calendar) cal.clone();

        calCopy.set(Calendar.HOUR_OF_DAY, 0);
        calCopy.set(Calendar.MINUTE, 0);
        calCopy.set(Calendar.SECOND, 0);
        calCopy.set(Calendar.MILLISECOND, 0);

        return calCopy.getTimeInMillis();
    }


}

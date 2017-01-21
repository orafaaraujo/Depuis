package com.orafaaraujo.depuis.view.helper;

import android.content.res.Resources;

import com.orafaaraujo.depuis.R;

/**
 * Created by rafael on 21/01/17.
 */

public class ElapsedDateTimeHelper {

    public static String getTime(Resources res, long timestamp) {

        long different = System.currentTimeMillis() - timestamp;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String correctTime = "";

        if (elapsedDays > 0) {
            correctTime = res.getString(R.string.elapsed_days, elapsedDays);
        } else if (elapsedHours > 0) {
            correctTime = res.getString(R.string.elapsed_hours, elapsedHours);
        } else if (elapsedMinutes > 0) {
            correctTime = res.getString(R.string.elapsed_minutes, elapsedMinutes);
        } else {
            correctTime = res.getString(R.string.elapsed_seconds, elapsedSeconds);
        }

        return correctTime;

    }

}

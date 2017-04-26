package com.orafaaraujo.depuis.helper;

import android.content.Context;

import com.orafaaraujo.depuis.R;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Class used to calculate the elapsed datetime since the @{@link com.orafaaraujo.depuis.model.Fact}
 * was created.
 *
 * Created by rafael on 21/01/17.
 */
@Reusable
public class ElapsedDateTimeHelper {

    @Inject
    Context mContext;

    @Inject
    ElapsedDateTimeHelper() {
    }

    /**
     * Method that return the formatted String of elapsed date and time.
     *
     * @param timestamp The elapsed time in milliseconds
     * @return The string formatted by the maximo time passed. For example, if was 15 minutes will
     * return "15m", but if is 20 days will return "20 days" and go on.
     */
    public String getTime(long timestamp) {

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
            correctTime = mContext.getString(R.string.elapsed_days, elapsedDays);
        } else if (elapsedHours > 0) {
            correctTime = mContext.getString(R.string.elapsed_hours, elapsedHours);
        } else if (elapsedMinutes > 0) {
            correctTime = mContext.getString(R.string.elapsed_minutes, elapsedMinutes);
        } else {
            correctTime = mContext.getString(R.string.elapsed_seconds, elapsedSeconds);
        }

        return correctTime;

    }

}

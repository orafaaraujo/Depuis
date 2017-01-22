package com.orafaaraujo.depuis.helper;

import android.content.Context;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;

import javax.inject.Inject;

/**
 * Created by rafael on 21/01/17.
 */

public class ElapsedDateTimeHelper {

    @Inject
    Context mContext;

    public ElapsedDateTimeHelper() {
        Injector.getApplicationComponent().inject(this);
    }

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

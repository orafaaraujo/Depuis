package com.orafaaraujo.depuis.helper;

import com.orafaaraujo.depuis.dagger.Injector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rafael on 21/01/17.
 */

public class DateTimeHelper {

    public DateTimeHelper() {
        Injector.getApplicationComponent().inject(this);
    }

    public String getTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}

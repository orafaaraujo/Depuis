package com.orafaaraujo.depuis.viewModel;

import android.databinding.BaseObservable;

import com.orafaaraujo.depuis.helper.DateTimeHelper;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by rafael on 25/01/17.
 */

public class NewFactViewModel extends BaseObservable {

    @Inject
    DateTimeHelper mDateTimeHelper;

    private long mTimeInMillis;

    public NewFactViewModel() {
        mTimeInMillis = mDateTimeHelper.getCleanTime(Calendar.getInstance());
    }

    public String getDate() {
        return mDateTimeHelper.getDate(mTimeInMillis);
    }

    public String getTime() {
        return mDateTimeHelper.getTime(mTimeInMillis);
    }
}

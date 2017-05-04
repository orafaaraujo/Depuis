package com.orafaaraujo.depuis.view.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helper.buses.DatetimeTO;
import com.orafaaraujo.depuis.helper.RxBus;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by rafael on 23/01/17.
 */

public class DatePickerFragment extends AppCompatDialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Inject
    RxBus mRxBus;

    private Calendar mCalendar;

    @Inject
    public DatePickerFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(getArguments().getLong("TimeInMillis"));

        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        mRxBus.sendEvent(
                DatetimeTO.builder()
                        .setMilliseconds(mCalendar.getTimeInMillis())
                        .build());
    }
}

package com.orafaaraujo.depuis.view.fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helper.buses.DatetimeTO;
import com.orafaaraujo.depuis.helper.RxBus;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by rafael on 23/01/17.
 */

public class TimePickerFragment extends AppCompatDialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Inject
    RxBus mRxBus;

    Calendar mCalendar;

    @Inject
    public TimePickerFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(getArguments().getLong("TimeInMillis"));

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);

        mRxBus.sendEvent(
                DatetimeTO.builder()
                        .setMilliseconds(mCalendar.getTimeInMillis())
                        .build());
    }
}

package com.orafaaraujo.depuis.view.fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

import java.util.Calendar;

/**
 * Created by rafael on 23/01/17.
 */

public class TimePickerFragment extends AppCompatDialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getArguments().getLong("TimeInMillis"));

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        ((NewFactActivity) getActivity())
                .getCalendarPublishSubject()
                .onNext(calendar.getTimeInMillis());
    }
}

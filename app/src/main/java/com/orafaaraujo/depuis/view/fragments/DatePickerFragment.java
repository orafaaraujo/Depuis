package com.orafaaraujo.depuis.view.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

import java.util.Calendar;

/**
 * Created by rafael on 23/01/17.
 */

public class DatePickerFragment extends AppCompatDialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getArguments().getLong("TimeInMillis"));

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        ((NewFactActivity) getActivity())
                .getCalendarPublishSubject()
                .onNext(calendar.getTimeInMillis());
    }
}

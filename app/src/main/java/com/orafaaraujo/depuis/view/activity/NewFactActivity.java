package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orafaaraujo.depuis.BR;
import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.view.fragments.DatePickerFragment;
import com.orafaaraujo.depuis.view.fragments.TimePickerFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @Inject
    DateTimeHelper mDateTimeHelper;

    @BindView(R.id.new_fact_text_edittext_title)
    TextInputEditText mTitle;

    @BindView(R.id.new_fact_text_edittext_comment)
    TextInputEditText mComment;

    private ViewDataBinding viewDataBinding;

    private Calendar mCalendar;

    public static final String CURRENT_DATE = "currentDate";
    public static final String CURRENT_TIME = "currentTime";

    @Override
    protected void onResume() {
        super.onResume();
        mCalendar = Calendar.getInstance(Locale.getDefault());

        viewDataBinding.setVariable(BR.new_fact_date, mDateTimeHelper.getDate(new Date().getTime()));
        viewDataBinding.setVariable(BR.new_fact_time, mDateTimeHelper.getTime(mDateTimeHelper.getCleanTime(mCalendar)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_fact);
        Injector.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Timber.tag("NewFact");
    }

    public void onBackButton(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    public void onStartNewFact(View view) {
        Timber.i("Title: %s - Comment: %s", mTitle.getText(), mComment.getText());
        onBackButton(view);
    }

    public void onDateClick(View view) {
        final Bundle bundle = new Bundle();
        bundle.putLong(CURRENT_DATE, mCalendar.getTimeInMillis());

        final DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), CURRENT_DATE);
    }

    public void onTimeClick(View view) {
        final Bundle bundle = new Bundle();
        bundle.putLong(CURRENT_TIME, mCalendar.getTimeInMillis());

        final DialogFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), CURRENT_TIME);
    }
}

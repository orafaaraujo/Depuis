package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.orafaaraujo.depuis.BR;
import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.view.fragments.DatePickerFragment;
import com.orafaaraujo.depuis.view.fragments.TimePickerFragment;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @Inject
    DateTimeHelper mDateTimeHelper;

    @BindView(R.id.new_fact_text_edittext_title)
    TextInputEditText mTitle;

    @BindView(R.id.new_fact_text_edittext_comment)
    TextInputEditText mComment;

    private final PublishSubject<Long> mCalendarPublishSubject = PublishSubject.create();

    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_fact);

        startComponents();
        startPublishSubject(binding);
    }

    private void startComponents() {
        Injector.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        Timber.tag("NewFact");
    }

    private void startPublishSubject(ViewDataBinding binding) {
        mCalendarPublishSubject.subscribe(timeMillis -> {
            mCalendar.setTimeInMillis(timeMillis);
            binding.setVariable(BR.new_fact_date, mDateTimeHelper.getDate(timeMillis));
            binding.setVariable(BR.new_fact_time, mDateTimeHelper.getTime(timeMillis));
        });

        mCalendar = Calendar.getInstance();
        mCalendarPublishSubject.onNext(mDateTimeHelper.getCleanTime(mCalendar));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCalendarPublishSubject.unsubscribeOn(Schedulers.io());
    }

    @OnClick(R.id.new_fact_button_back)
    public void onBackButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    @OnClick(R.id.new_fact_start_button)
    public void onStartNewFact() {
        if (TextUtils.isEmpty(mTitle.getText())) {
            mTitle.setError(getString(R.string.new_fact_title_empty));
            return;
        }
        saveNewFact();
    }

    private void saveNewFact() {
        Fact fact = Fact.builder()
                .setTitle(mTitle.getText().toString())
                .setComment(mComment.getText().toString())
                .setCount(true)
                .setTimestamp(mCalendar.getTimeInMillis())
                .build();
        Timber.i("New fact: %s", fact.toString());
        onBackButton();
    }

    @OnClick(R.id.new_fact_text_date)
    public void onDateClick(View view) {
        final DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(getCurrentTime());
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.new_fact_text_time)
    public void onTimeClick(View view) {
        final DialogFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(getCurrentTime());
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getCurrentTime() {
        final Bundle bundle = new Bundle();
        bundle.putLong("TimeInMillis", mCalendar.getTimeInMillis());
        return bundle;
    }

    public PublishSubject<Long> getCalendarPublishSubject() {
        return mCalendarPublishSubject;
    }
}

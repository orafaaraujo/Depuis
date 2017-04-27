package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.databinding.ActivityNewFactBinding;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.DatetimeTO;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackTO;
import com.orafaaraujo.depuis.view.fragments.DatePickerFragment;
import com.orafaaraujo.depuis.view.fragments.TimePickerFragment;
import com.orafaaraujo.depuis.viewModel.NewFactViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @Inject
    NewFactViewModel mNewFactViewModel;

    @Inject
    DateTimeHelper mDateTimeHelper;

    @Inject
    RxBus mRxBus;

    @Inject
    DatePickerFragment mDatePickerFragment;

    @Inject
    TimePickerFragment mTimePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("NewFact");

        Injector.getApplicationComponent().inject(this);

        final ActivityNewFactBinding newFactBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_new_fact);
        newFactBinding.setViewModel(mNewFactViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleEvents();
    }

    private void handleEvents() {
        mRxBus.getEvents()
                .filter(o -> o instanceof DatetimeTO)
                .map(o -> (DatetimeTO) o)
                .subscribe(vo -> mNewFactViewModel.setMilliseconds(vo.milliseconds()), Timber::e);

        mRxBus.getEvents()
                .filter(o -> o instanceof NewFactFeedbackTO)
                .map(o -> (NewFactFeedbackTO) o)
                .subscribe(this::handlerNewFactFeedback, Timber::e);

        mRxBus.sendEvent(
                DatetimeTO.builder()
                        .setMilliseconds(mDateTimeHelper.getCleanTime())
                        .build());
    }

    private void handlerNewFactFeedback(NewFactFeedbackTO vo) {
        if (vo.success()) {
            onBackButton(null);
        } else {
            showSnackMessage(vo.message());
        }
    }

    private void showSnackMessage(String s) {
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.new_fact_text_title), s,
                Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    public void onBackButton(View view) {
        supportFinishAfterTransition();
    }

    public void onDateClick(View view) {
        mDatePickerFragment.setArguments(getCurrentTime());
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onTimeClick(View view) {
        mTimePickerFragment.setArguments(getCurrentTime());
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getCurrentTime() {
        final Bundle bundle = new Bundle();
        bundle.putLong("TimeInMillis", mNewFactViewModel.getMilliseconds());
        return bundle;
    }
}
package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.databinding.ActivityNewFactBinding;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.buses.DatetimeVO;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackVO;
import com.orafaaraujo.depuis.helper.buses.RxBus;
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

    @Override
    protected void onStop() {
        super.onStop();
        completeObservables();
    }

    private void handleEvents() {
        mRxBus.getEvents()
                .filter(o -> o instanceof DatetimeVO)
                .map(o -> (DatetimeVO) o)
                .subscribe(vo -> mNewFactViewModel.setMilliseconds(vo.milliseconds()), Timber::e);

        mRxBus.getEvents()
                .filter(o -> o instanceof NewFactFeedbackVO)
                .map(o -> (NewFactFeedbackVO) o)
                .subscribe(this::handlerNewFactFeedback, Timber::e);

        mRxBus.sendEvent(
                DatetimeVO.builder()
                        .setMilliseconds(mDateTimeHelper.getCleanTime())
                        .build());
    }

    private void handlerNewFactFeedback(NewFactFeedbackVO vo) {
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

    private void completeObservables() {
        mRxBus.complete();
    }

    public void onBackButton(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
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

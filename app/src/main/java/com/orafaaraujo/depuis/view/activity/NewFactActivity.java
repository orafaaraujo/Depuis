package com.orafaaraujo.depuis.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.databinding.ActivityNewFactBinding;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.DatetimeTO;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackTO;
import com.orafaaraujo.depuis.view.bindingadapter.DepuisDataBindingComponent;
import com.orafaaraujo.depuis.view.fragments.DatePickerFragment;
import com.orafaaraujo.depuis.view.fragments.TimePickerFragment;
import com.orafaaraujo.depuis.viewModel.NewFactViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @Inject
    NewFactViewModel mNewFactViewModel;

    @Inject
    DepuisDataBindingComponent mBindingComponent;

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

        final ActivityNewFactBinding newFactBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_new_fact, mBindingComponent);
        newFactBinding.setViewModel(mNewFactViewModel);

        newFactBinding.newFactTextEdittextTitle.setOnFocusChangeListener(
                (v, hasFocus) -> {
                    if (v.getId() == R.id.new_fact_text_edittext_title && !hasFocus) {


                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleEvents();
    }

    private void handleEvents() {
        mRxBus.getDatetimeEvents()
                .subscribe(vo -> mNewFactViewModel.setMilliseconds(vo.milliseconds()), Timber::e);

        mRxBus.getNewFactFeedbackEvents()
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
        Snackbar.make(findViewById(R.id.new_fact_text_title), s, Snackbar.LENGTH_SHORT).show();
    }

    public void onBackButton(View view) {
        supportFinishAfterTransition();
    }

    public void onDateClick(View view) {
        closeKeyboard(view);
        mDatePickerFragment.setArguments(getCurrentTime());
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onTimeClick(View view) {
        closeKeyboard(view);
        mTimePickerFragment.setArguments(getCurrentTime());
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getCurrentTime() {
        final Bundle bundle = new Bundle();
        bundle.putLong("TimeInMillis", mNewFactViewModel.getMilliseconds());
        return bundle;
    }

    private void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
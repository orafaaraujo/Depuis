package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.IntPreference;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackTO;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.repository.database.FactDatabase;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by rafael on 25/01/17.
 */

public class NewFactViewModel extends BaseObservable {

    @Inject
    RxBus mRxBus;

    @Inject
    Context mContext;

    @Inject
    DateTimeHelper mDateTimeHelper;

    @Inject
    FactDatabase mDatabase;

    @Inject
    IntPreference mIntPreference;

    public final ObservableField<String> titleFact = new ObservableField<>("");

    public final ObservableField<String> commentFact = new ObservableField<>("");

    private long mMilliseconds;

    private boolean mTextIsFilled = false;

    @Inject
    NewFactViewModel() {
    }

    public long getMilliseconds() {
        return mMilliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        mMilliseconds = milliseconds;
        notifyChange();
    }

    public String getDate() {
        return mDateTimeHelper.getDate(mMilliseconds);
    }

    public String getTime() {
        return mDateTimeHelper.getTime(mMilliseconds);
    }

    public View.OnClickListener onClickStart() {
        return v -> saveNewFact();
    }

    private void saveNewFact() {
        if (mTextIsFilled) {
            mDatabase.saveFact(createFact());
        }
        validateFact();
    }

    private Fact createFact() {

        // Using this approach because Requery doesn't support auto increment key
        // when using AutoValue.
        final int key = mIntPreference.get();
        mIntPreference.set(1 + key);

        return Fact.builder()
                .setId(key)
                .setTitle(titleFact.get())
                .setComment(commentFact.get())
                .setCount(true)
                .setStartTime(mMilliseconds)
                .build();
    }

    private void validateFact() {
        if (mTextIsFilled) {
            Timber.i("Fact complete");
            mRxBus.sendEvent(NewFactFeedbackTO.builder()
                    .setSuccess(true)
                    .setMessage(mContext.getString(R.string.new_fact_success))
                    .build());
        } else {
            Timber.i("Title not filled");
            mRxBus.sendEvent(NewFactFeedbackTO.builder()
                    .setSuccess(false)
                    .setMessage(mContext.getString(R.string.new_fact_failure_title))
                    .build());
        }
    }

    public TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTextIsFilled = !TextUtils.isEmpty(s.toString());
            }
        };
    }
}
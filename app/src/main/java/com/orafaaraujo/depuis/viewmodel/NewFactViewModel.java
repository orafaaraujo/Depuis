package com.orafaaraujo.depuis.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackTO;
import com.orafaaraujo.depuis.model.FactModel;
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

    private long mMilliseconds;

    public final ObservableField<String> titleFact = new ObservableField<>("");

    public final ObservableField<String> commentFact = new ObservableField<>("");

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
            long factId = mDatabase.saveFact(createFact());
            newFaceSuccessFeedback(factId);
        } else {
            newFaceFailureFeedback();
        }
    }

    private FactModel createFact() {

        return FactModel.builder()
                .setId(-1) // Will be replaced by Database.
                .setStartTime(mMilliseconds)
                .setTitle(titleFact.get())
                .setComment(commentFact.get())
                .setEndTime(-1)
                .build();
    }

    private void newFaceSuccessFeedback(long factId) {

        Timber.i("FactModel complete");

        mRxBus.sendEvent(NewFactFeedbackTO.builder()
                .setSuccess(true)
                .setMessage(mContext.getString(R.string.new_fact_success))
                .build());

        FactModel savedFactModel = FactModel.builder()
                .setId(factId)
                .setStartTime(0) // Ignored field
                .setTitle("") // Ignored field
                .setComment("") // Ignored field
                .setEndTime(0) // Ignored field
                .build();

        mRxBus.sendEvent(FactTO.builder()
                .setFact(savedFactModel)
                .setNewFact(true)
                .setDelete(false)
                .setClose(false)
                .setPosition(0)
                .build());
    }

    private void newFaceFailureFeedback() {
        Timber.i("Title not filled");
        mRxBus.sendEvent(NewFactFeedbackTO.builder()
                .setSuccess(false)
                .setMessage(mContext.getString(R.string.new_fact_failure_title))
                .build());
    }

    public TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequences, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence sequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextIsFilled = !TextUtils.isEmpty(editable.toString().trim());
            }
        };
    }

    public int getDateIcon() {
        return R.drawable.ic_today_22dp;
    }

    public int getTimeIcon() {
        return R.drawable.ic_access_time_22dp;
    }

}
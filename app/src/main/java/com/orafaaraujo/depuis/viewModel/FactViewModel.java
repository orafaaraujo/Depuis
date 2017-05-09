package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.ElapsedDateTimeHelper;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.ShareContentHelper;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.Fact;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by rafael on 18/01/17.
 */

public class FactViewModel extends BaseObservable {

    @Inject
    Context mContext;

    @Inject
    ElapsedDateTimeHelper mElapsedDateTimeHelper;

    @Inject
    DateTimeHelper mDateTimeHelper;

    @Inject
    Lazy<ShareContentHelper> mShareContentHelper;

    @Inject
    RxBus mRxBus;

    private Fact mFact;

    @Inject
    FactViewModel() {
    }

    public void setFact(Fact fact) {
        mFact = fact;
    }

    public String getFactTitle() {
        return mFact.title();
    }

    public String getFactComment() {
        return mFact.comment();
    }

    public String getFactBegin() {
        return mDateTimeHelper.getDayDate(mFact.startTime());
    }

    public String getFactCurrentTime() {
        return mElapsedDateTimeHelper.getTime(mFact.startTime(), mFact.endTime());
    }

    public View.OnClickListener onClickShare() {
        return v -> mShareContentHelper.get().share(mFact);
    }

    public View.OnClickListener onCloseFact() {
        return v ->
                mRxBus.sendEvent(
                        FactTO.builder()
                                .setFact(mFact)
                                .setPosition(0)
                                .setDelete(false)
                                .setClose(true)
                                .build());
    }

}

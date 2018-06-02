package com.orafaaraujo.depuis.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.ElapsedDateTimeHelper;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.ShareContentHelper;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.FactModel;

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

    private int mPosition;

    private FactModel mFactModel;

    @Inject
    FactViewModel() {
    }

    public void setFactModel(FactModel factModel) {
        mFactModel = factModel;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public String getFactTitle() {
        return mFactModel.title();
    }

    public String getFactComment() {
        return mFactModel.comment();
    }

    public String getFactBegin() {
        return mDateTimeHelper.getDayDate(mFactModel.startTime());
    }

    public String getFactCurrentTime() {
        return mElapsedDateTimeHelper.getTime(mFactModel.startTime(), mFactModel.endTime());
    }

    public View.OnClickListener onClickShare() {
        return v -> mShareContentHelper.get().share(mFactModel);
    }

    public View.OnClickListener onCloseFact() {
        return v ->
                mRxBus.sendEvent(
                        FactTO.builder()
                                .setFact(mFactModel)
                                .setPosition(mPosition)
                                .setClose(true)
                                .setNewFact(false)
                                .setDelete(false)
                                .build());
    }

    public boolean alreadyClosed() {
        return mFactModel.endTime() == -1;
    }

    public int getCardClosedBackground() {
        return alreadyClosed() ? android.R.color.white : R.color.main_close_fact_background;
    }

    public int getCardClosedIcon() {
        return alreadyClosed() ? R.drawable.ic_lock_open_black_24dp : R.drawable.ic_lock_22dp;
    }

    public int getBeginIcon() {
        return R.drawable.ic_access_time_22dp;
    }

    public int getShareIcon() {
        return R.drawable.ic_share_18dp;
    }

}

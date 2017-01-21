package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.view.helper.DateTimeHelper;
import com.orafaaraujo.depuis.view.helper.ElapsedDateTimeHelper;

/**
 * Created by rafael on 18/01/17.
 */

public class FactViewModel extends BaseObservable {

    private Context mContext;
    private Fact mFact;

    public FactViewModel(Context context, Fact fact) {
        mContext = context;
        mFact = fact;
    }

    public String getFactTitle() {
        return mFact.title();
    }

    public String getFactComment() {
        return mFact.comment();
    }

    public String getFactBegin() {
        return DateTimeHelper.getTime(mFact.timestamp());
    }

    public String getFactCurrentTime() {
        return ElapsedDateTimeHelper.getTime(mContext.getResources(), mFact.timestamp());
    }

    public boolean getFactCount() {
        return mFact.count();
    }


}

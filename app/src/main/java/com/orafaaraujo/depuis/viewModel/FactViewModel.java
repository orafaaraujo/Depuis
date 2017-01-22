package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.ElapsedDateTimeHelper;
import com.orafaaraujo.depuis.helper.ShareContentHelper;

import javax.inject.Inject;

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
    ShareContentHelper mShareContentHelper;

    private Fact mFact;

    public FactViewModel(Fact fact) {
        mFact = fact;
        Injector.getApplicationComponent().inject(this);
    }

    public String getFactTitle() {
        return mFact.title();
    }

    public String getFactComment() {
        return mFact.comment();
    }

    public String getFactBegin() {
        return mDateTimeHelper.getTime(mFact.timestamp());
    }

    public String getFactCurrentTime() {
        return mElapsedDateTimeHelper.getTime(mFact.timestamp());
    }

    public boolean getFactCount() {
        return mFact.count();
    }

    public View.OnClickListener onClickShare() {
        return v -> mShareContentHelper.share(mFact);
    }

}

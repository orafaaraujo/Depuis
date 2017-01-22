package com.orafaaraujo.depuis.helper;

import android.content.Context;
import android.content.Intent;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.model.Fact;

import javax.inject.Inject;

/**
 * Created by rafael on 21/01/17.
 */

public class ShareContentHelper {

    @Inject
    Context mContext;

    @Inject
    ElapsedDateTimeHelper mElapsedDateTimeHelper;

    public ShareContentHelper() {
        Injector.getApplicationComponent().inject(this);
    }

    public void share(Fact fact) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, makeText(mContext, fact));
        sendIntent.setType("text/plain");
        mContext.startActivity(Intent.createChooser(sendIntent, mContext.getString(R.string.fact_share_message)));
    }

    private String makeText(Context context, Fact fact) {
        return context.getString(
                R.string.fact_share_fact,
                mElapsedDateTimeHelper.getTime(fact.timestamp()),
                fact.title()
        );
    }

}

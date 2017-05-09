package com.orafaaraujo.depuis.helper;

import android.content.Context;
import android.content.Intent;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.Fact;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Class to handle all shareable content.
 *
 * Created by rafael on 21/01/17.
 */
@Reusable
public class ShareContentHelper {

    @Inject
    Context mContext;

    @Inject
    ElapsedDateTimeHelper mElapsedDateTimeHelper;

    @Inject
    ShareContentHelper() {
    }

    /**
     * Method to share a fact on social networks.
     *
     * @param fact Fact that user want to share to.
     */
    public void share(Fact fact) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, makeText(mContext, fact));
        sendIntent.setType("text/plain");
        mContext.startActivity(
                Intent.createChooser(sendIntent, mContext.getString(R.string.fact_share_message)));
    }

    private String makeText(Context context, Fact fact) {
        return context.getString(
                R.string.fact_share_fact,
                mElapsedDateTimeHelper.getTime(fact.startTime(), fact.endTime()),
                fact.title()
        );
    }

}

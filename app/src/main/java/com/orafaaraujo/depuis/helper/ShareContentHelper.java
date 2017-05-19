package com.orafaaraujo.depuis.helper;

import android.content.Context;
import android.content.Intent;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.FactModel;

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
     * Method to share a factModel on social networks.
     *
     * @param factModel FactModel that user want to share to.
     */
    public void share(FactModel factModel) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, makeText(mContext, factModel));
        sendIntent.setType("text/plain");
        mContext.startActivity(
                Intent.createChooser(sendIntent, mContext.getString(R.string.fact_share_message)));
    }

    private String makeText(Context context, FactModel factModel) {
        return context.getString(
                R.string.fact_share_fact,
                mElapsedDateTimeHelper.getTime(factModel.startTime(), factModel.endTime()),
                factModel.title()
        );
    }

}

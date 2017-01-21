package com.orafaaraujo.depuis.view.helper;

import android.content.Context;
import android.content.Intent;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.Fact;

/**
 * Created by rafael on 21/01/17.
 */

public class ShareContentHelper {


    public static void share(Context context, Fact fact) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, makeText(context, fact));
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.fact_share_message)));
    }

    private static String makeText(Context context, Fact fact) {
        return context.getString(R.string.fact_share_fact,
                ElapsedDateTimeHelper.getTime(context.getResources(), fact.timestamp()),
                fact.title());
    }

}

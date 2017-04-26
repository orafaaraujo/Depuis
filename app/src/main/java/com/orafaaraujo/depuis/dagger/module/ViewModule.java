package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by venturus on 25/04/17.
 */
@Module
public class ViewModule {

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    DividerItemDecoration provideDividerItemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

}

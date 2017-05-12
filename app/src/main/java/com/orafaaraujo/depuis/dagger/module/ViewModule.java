package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;

/**
 * Module to handle with Views and their components.
 *
 * Created by rafael on 22/01/17.
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

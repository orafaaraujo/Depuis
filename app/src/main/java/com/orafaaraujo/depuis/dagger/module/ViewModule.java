package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orafaaraujo.depuis.view.helper.SimpleItemTouchHelperCallback;

import javax.inject.Singleton;

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
    @Singleton
    DividerItemDecoration provideDividerItemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    /**
     * Must implement the concrete class, because
     * @{@link android.support.v7.widget.helper.ItemTouchHelper.android.support.v7.widget.helper.ItemTouchHelper.Callback}
     * is a static abstract classe.
     */
    @Provides
    @Singleton
    SimpleItemTouchHelperCallback provideItemTouchHelperCallback(Context context) {
        return new SimpleItemTouchHelperCallback(context);
    }


}

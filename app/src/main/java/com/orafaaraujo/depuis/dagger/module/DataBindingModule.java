package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.view.bindingadapter.BindingAdapterHelper;
import com.orafaaraujo.depuis.view.bindingadapter.DepuisDataBindingComponent;
import com.orafaaraujo.depuis.view.bindingadapter.FontCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Module to hold DataBindingAdapter and their components.
 *
 * Created by Rafael Araujo on 12/05/17.
 */
@Module
public class DataBindingModule {

    @Provides
    @Singleton
    FontCache provideFontCache(Context context) {
        return new FontCache(context);
    }

    @Provides
    @Singleton
    BindingAdapterHelper provideBindingAdapterHelper(FontCache fontCache) {
        return new BindingAdapterHelper(fontCache);
    }

    @Provides
    @Reusable
    DepuisDataBindingComponent provideDataBindingComponent(BindingAdapterHelper adapter) {
        return new DepuisDataBindingComponent(adapter);
    }
}

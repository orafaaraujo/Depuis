package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.DepuisApp;
import com.orafaaraujo.depuis.helper.ShareContentHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class ApplicationModule {

    private final DepuisApp mApplication;

    public ApplicationModule(DepuisApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public DepuisApp application() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context applicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Reusable
    public ShareContentHelper shareContentHelper() {
        return new ShareContentHelper();
    }
}

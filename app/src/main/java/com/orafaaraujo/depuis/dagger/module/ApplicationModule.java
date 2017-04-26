package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.DepuisApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    Context applicationContext() {
        return mApplication.getApplicationContext();
    }

}

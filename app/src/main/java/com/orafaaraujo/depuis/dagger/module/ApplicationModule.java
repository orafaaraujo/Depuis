package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.DepuisApp;
import com.orafaaraujo.depuis.helper.ShareContentHelper;
import com.orafaaraujo.depuis.helper.buses.RxBus;

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
    Context applicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Reusable
    ShareContentHelper shareContentHelper() {
        return new ShareContentHelper();
    }

    @Provides
    @Reusable
    RxBus getRxBus() {
        return new RxBus();
    }
}

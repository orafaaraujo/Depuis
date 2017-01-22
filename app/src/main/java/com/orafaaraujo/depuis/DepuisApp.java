package com.orafaaraujo.depuis;

import android.app.Application;

import com.orafaaraujo.depuis.dagger.Injector;

import timber.log.Timber;

/**
 * Created by rafael on 22/01/17.
 */

public class DepuisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        initDagger();
    }

    private void initDagger() {
        Injector.initializeApplicationComponent(this);
        Injector.getApplicationComponent().inject(this);
    }
}

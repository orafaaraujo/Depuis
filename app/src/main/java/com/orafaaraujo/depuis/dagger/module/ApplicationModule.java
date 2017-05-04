package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.orafaaraujo.depuis.DepuisApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class ApplicationModule {

    private final DepuisApplication mApplication;

    public ApplicationModule(DepuisApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public DepuisApplication application() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context applicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(DepuisApplication app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}

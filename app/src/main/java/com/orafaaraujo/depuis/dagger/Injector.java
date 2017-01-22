package com.orafaaraujo.depuis.dagger;

import android.os.Build;

import com.orafaaraujo.depuis.DepuisApp;
import com.orafaaraujo.depuis.dagger.module.ApplicationModule;
import com.orafaaraujo.depuis.dagger.module.DatabaseModule;
import com.orafaaraujo.depuis.dagger.module.FormatterModule;

import java.util.Objects;

/**
 * Created by rafael on 22/01/17.
 */

public class Injector {

    private static ApplicationComponent sApplicationComponent;

    public Injector() {
    }

    public static void initializeApplicationComponent(DepuisApp application) {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .databaseModule(new DatabaseModule())
                .formatterModule(new FormatterModule())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(sApplicationComponent, "ApplicationComponent is null");
        } else {
            if (sApplicationComponent == null) {
                throw new NullPointerException("ApplicationComponent is null");
            }
        }
        return sApplicationComponent;
    }
}

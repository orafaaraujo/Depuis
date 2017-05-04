package com.orafaaraujo.depuis.dagger;

import com.orafaaraujo.depuis.DepuisApplication;
import com.orafaaraujo.depuis.dagger.module.ApplicationModule;
import com.orafaaraujo.depuis.dagger.module.DatabaseModule;
import com.orafaaraujo.depuis.dagger.module.ViewModule;
import com.orafaaraujo.depuis.view.activity.MainActivity;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rafael on 22/01/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ViewModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(DepuisApplication depuisApplication);

    void inject(MainActivity mainActivity);

    void inject(NewFactActivity newFactActivity);
}

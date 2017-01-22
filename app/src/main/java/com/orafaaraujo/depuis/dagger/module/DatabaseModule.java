package com.orafaaraujo.depuis.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class DatabaseModule {

    public DatabaseModule() {
    }

    @Provides
    @Singleton
    String provideDatabase() {
        return "database";
    }

}

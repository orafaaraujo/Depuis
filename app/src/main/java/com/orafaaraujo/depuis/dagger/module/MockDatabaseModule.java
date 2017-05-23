package com.orafaaraujo.depuis.dagger.module;

import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.MockDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module to hold mock Database and their components.
 *
 * Created by rafael on 22/01/17.
 */
@Module
public class MockDatabaseModule {

    @Provides
    @Singleton
    FactDatabase provideDatabase() {
        return new MockDatabase(true);
    }
}
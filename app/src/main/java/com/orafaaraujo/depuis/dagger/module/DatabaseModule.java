package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    FactDatabase provideDatabase(Context context) {
//        return new MockDatabase(false);
        return new SQLiteDatabase(context);
    }

}

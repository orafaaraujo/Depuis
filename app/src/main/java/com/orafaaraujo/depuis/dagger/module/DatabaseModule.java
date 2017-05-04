package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.orafaaraujo.depuis.helper.IntPreference;
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
//        return new MockDatabase();
//        return new RequeryDatabase(entityStore);
        return new SQLiteDatabase(context);
    }


    @Provides
    @Singleton
    IntPreference provideDatabaseKey(SharedPreferences prefs) {
        return new IntPreference(prefs, "databaseKey", 0);
    }

}

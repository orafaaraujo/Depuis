package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.orafaaraujo.depuis.BuildConfig;
import com.orafaaraujo.depuis.helper.IntPreference;
import com.orafaaraujo.depuis.model.Models;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.RequeryDatabase;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.meta.EntityModel;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.ConfigurationBuilder;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    FactDatabase provideDatabase(ReactiveEntityStore<Persistable> entityStore) {
//        return new MockDatabase();
        return new RequeryDatabase(entityStore);
    }

    @Provides
    @Singleton
    ReactiveEntityStore<Persistable> provideEntityStore(Configuration configuration) {
        return ReactiveSupport
                .toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    @Provides
    @Singleton
    Configuration provideConfiguration(DatabaseSource source, EntityModel model) {
        return new ConfigurationBuilder(source, model)
                .useDefaultLogging()
                .setWriteExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    @Provides
    @Singleton
    EntityModel provideModels() {
        return Models.DEFAULT;
    }

    @Provides
    @Singleton
    DatabaseSource provideDatabaseSource(Context context) {
        final DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);
        if (BuildConfig.DEBUG) {
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        return source;
    }

    @Provides
    @Singleton
    IntPreference provideDatabaseKey(SharedPreferences prefs) {
        return new IntPreference(prefs, "databaseKey", 0);
    }

}

package com.orafaaraujo.depuis.dagger.module;

import android.content.Context;

import com.orafaaraujo.depuis.BuildConfig;
import com.orafaaraujo.depuis.repository.entity.Models;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    ReactiveEntityStore<Persistable> provideDatabase(Configuration configuration) {
        return ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    @Provides
    @Singleton
    Configuration provideConfiguration(DatabaseSource source) {
        return source.getConfiguration();
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

}

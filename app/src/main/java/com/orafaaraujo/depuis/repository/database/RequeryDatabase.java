package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.model.Fact;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import timber.log.Timber;

public class RequeryDatabase implements FactDatabase {

    ReactiveEntityStore<Persistable> mDatabase;

    public RequeryDatabase(ReactiveEntityStore<Persistable> database) {
        mDatabase = database;
    }

    @Override
    public void saveFact(Fact fact) {
        mDatabase
                .insert(fact)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(factEntity -> Timber.i(factEntity.toString()));
    }

    @Override
    public List<Fact> fetchAll() {
        return mDatabase
                .select(Fact.class)
                .get()
                .toList();
    }

    @Override
    public void deleteFact(Fact fact) {
        mDatabase
                .delete(fact)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(factEntity -> Timber.i(factEntity.toString()));
    }
}

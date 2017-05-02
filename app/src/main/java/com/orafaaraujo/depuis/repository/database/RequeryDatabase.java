package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.model.FactType;

import java.util.List;

import io.reactivex.Observable;
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
    public Observable<Fact> getObservable() {
        return mDatabase
                .select(Fact.class)
                .get()
                .observable();
    }

    @Override
    public Fact fetchFact(int id) {
        return mDatabase
                .select(Fact.class)
                .where(FactType.ID.eq(id))
                .get()
                .first();
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

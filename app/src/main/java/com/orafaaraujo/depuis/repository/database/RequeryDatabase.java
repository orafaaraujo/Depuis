package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.repository.entity.FactEntity;
import com.orafaaraujo.depuis.repository.entity.FactEntityType;

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
    public void saveFact(FactEntity fact) {
        mDatabase.insert(fact)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(factEntity -> Timber.i(factEntity.toString()));
    }

    @Override
    public List<FactEntity> fetchAll() {
        return mDatabase
                .select(FactEntity.class)
                .get()
                .toList();
    }

    @Override
    public Observable<FactEntity> getObservable() {
        return mDatabase.select(FactEntity.class)
                .get()
                .observable();
    }

    @Override
    public FactEntity fetchFact(int id) {
        return mDatabase
                .select(FactEntity.class)
                .where(FactEntityType.ID.eq(id))
                .get()
                .first();
    }

    @Override
    public void deleteFact(FactEntity fact) {
        mDatabase.delete(fact);
    }
}

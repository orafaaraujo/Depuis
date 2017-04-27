package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.repository.entity.FactEntity;
import com.orafaaraujo.depuis.repository.entity.FactEntityType;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

@Singleton
public class RequeryDatabase implements FactDatabaseInterface {

    @Inject
    ReactiveEntityStore<Persistable> mDatabase;

    @Inject
    RequeryDatabase() {
    }

    @Override
    public boolean saveFact(FactEntity fact) {
        mDatabase
                .insert(fact)
                .blockingGet();
        return fetchFact(fact.id()) != null;
    }

    @Override
    public List<FactEntity> fetchAll() {
        return mDatabase
                .select(FactEntity.class)
                .get().
                        toList();
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
    public boolean deleteFact(FactEntity fact) {
        mDatabase
                .delete(fact);
        return fetchFact(fact.id()) == null;
    }
}

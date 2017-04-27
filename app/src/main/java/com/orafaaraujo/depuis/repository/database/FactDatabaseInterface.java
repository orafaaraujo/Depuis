package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.repository.entity.FactEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rafael on 06/02/17.
 */

public interface FactDatabaseInterface {

    boolean saveFact(FactEntity fact);

    List<FactEntity> fetchAll();

    Observable<FactEntity> getObservable();

    FactEntity fetchFact(int id);

    boolean deleteFact(FactEntity fact);

}

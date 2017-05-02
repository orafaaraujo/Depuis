package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.model.Fact;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rafael on 06/02/17.
 */

public interface FactDatabase {

    void saveFact(Fact fact);

    List<Fact> fetchAll();

    Observable<Fact> getObservable();

    Fact fetchFact(int id);

    void deleteFact(Fact fact);

}

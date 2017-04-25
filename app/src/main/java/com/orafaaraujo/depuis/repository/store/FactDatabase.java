package com.orafaaraujo.depuis.repository.store;

import com.orafaaraujo.depuis.repository.entity.FactEntity;

import java.util.List;

/**
 * Created by rafael on 06/02/17.
 */

public interface FactDatabase {

    boolean saveFact();

    List<FactEntity> fetchAll();

    FactEntity fetchFact(int id);

    boolean deleteFact();

}

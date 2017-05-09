package com.orafaaraujo.depuis.repository.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.Fact;

import java.util.List;

/**
 * Interface to expose all method used the CRUD of Fact.
 *
 * Created by rafael on 06/02/17.
 */
public interface FactDatabase {

    /**
     * Fact that need to be saved.
     *
     * @return The id of fact inserted.
     */
    long saveFact(Fact fact);

    /**
     * Fact that need to be update.
     */
    void updateFact(Fact fact);

    /**
     * Get the fact in parameter (using the _ID).
     * * @return Fact founded in Database. Otherwise, return a null object.
     */
    @Nullable
    Fact findFact(long FactId);

    /**
     * Get all Facts in Database. Must return a empty List if there's no records.
     *
     * @return All facts in Database. Otherwise, return a empty list.
     */
    @NonNull
    List<Fact> fetchAll();

    /**
     * Delete the fact in parameter (using the _ID).
     */
    void deleteFact(Fact fact);

    /**
     * Delete all records of Fact on Database.
     */
    void deleteTable();

}

package com.orafaaraujo.depuis.repository.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.FactModel;

import java.util.List;

/**
 * Interface to expose all method used the CRUD of FactModel.
 *
 * Created by rafael on 06/02/17.
 */
public interface FactDatabase {

    /**
     * FactModel that need to be saved.
     *
     * @return The id of factModel inserted.
     */
    long saveFact(FactModel factModel);

    /**
     * FactModel that need to be update.
     */
    void updateFact(FactModel factModel);

    /**
     * Get the fact in parameter (using the _ID).
     * * @return FactModel founded in Database. Otherwise, return a null object.
     */
    @Nullable
    FactModel findFact(long factId);

    /**
     * Get all Facts in Database. Must return a empty List if there's no records.
     *
     * @return All facts in Database. Otherwise, return a empty list.
     */
    @NonNull
    List<FactModel> fetchAll();

    /**
     * Delete the factModel in parameter (using the _ID).
     */
    void deleteFact(FactModel factModel);

    /**
     * Delete all records of FactModel on Database.
     */
    void deleteTable();

}

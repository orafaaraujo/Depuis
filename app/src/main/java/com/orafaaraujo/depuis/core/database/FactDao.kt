package com.orafaaraujo.depuis.core.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.orafaaraujo.depuis.core.model.Fact

@Dao
interface FactDao {

    @Query("SELECT * FROM fact")
    fun getAllFacts(): List<Fact>

    @Query("SELECT * FROM fact WHERE id = :id")
    fun getFactById(id: Long): Fact?

    @Insert(onConflict = REPLACE)
    fun insert(fact: Fact)

    @Delete
    fun delete(fact: Fact)
}
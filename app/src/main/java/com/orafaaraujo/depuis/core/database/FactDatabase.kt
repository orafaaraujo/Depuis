package com.orafaaraujo.depuis.core.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.orafaaraujo.depuis.core.model.Fact

@Database(entities = [(Fact::class)], version = 1, exportSchema = false)
abstract class FactDatabase : RoomDatabase() {

    abstract fun factDao(): FactDao
}

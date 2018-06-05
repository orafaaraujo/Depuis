package com.orafaaraujo.depuis.core.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Fact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String?,
    val startTime: Long,
    var endTime: Long?,
    var active: Boolean = true
)

package com.orafaaraujo.depuis.core.model

data class Fact(
        val id: Int,
        val title: String,
        val description: String,
        val startTime: Long,
        val endTime: Long,
        val active: Boolean)
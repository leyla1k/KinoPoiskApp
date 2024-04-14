package com.example.android_task.model.simple

import retrofit2.http.Query

data class Review(
    val id:Int,
    val title: String?,
    val type: String?,
    val review: String?,
)

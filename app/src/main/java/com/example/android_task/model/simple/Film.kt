package com.example.android_task.model.simple


data class Film(
    val id: Int, val name: String?, val description: String?, val poster: Poster?,
    val rating: FilmRating?
)

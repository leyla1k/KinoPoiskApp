package com.example.android_task.model.simple


data class Film(// variables for our first name,
    // last name, email and avatar
    val id: Int, val name: String?, val description: String?, val poster: Poster?,

    val rating: FilmRating?,
    /*   val poster: List<String>?,*/
)

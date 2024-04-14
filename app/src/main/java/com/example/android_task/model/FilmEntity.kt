package com.example.android_task.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_task.model.simple.Poster

@Entity
data class FilmEntity(// variables for our first name,
    // last name, email and avatar
    @PrimaryKey val id: Int, val name: String?, val description: String?, val poster: Poster?

    /*   val rating: String?,*/
    /*   val poster: List<String>?,*/
)

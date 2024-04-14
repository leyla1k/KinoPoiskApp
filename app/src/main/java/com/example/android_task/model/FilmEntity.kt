package com.example.android_task.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_task.model.simple.FilmRating
import com.example.android_task.model.simple.Poster
import com.example.android_task.utils.Global

@Entity(tableName = Global.TABLE_NAME)
data class FilmEntity(
    @PrimaryKey val id: Int, val name: String?, val description: String?, val poster: Poster?,
    @Embedded val rating: FilmRating?
)

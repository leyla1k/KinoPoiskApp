package com.example.android_task.model.dto


import com.example.android_task.model.simple.FilmRating
import com.example.android_task.model.simple.Poster
import com.google.gson.annotations.SerializedName

data class FilmResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("poster")
    val posters: Poster?,
    @SerializedName("rating")
    val rating: FilmRating?
)






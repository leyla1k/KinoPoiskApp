package com.example.android_task.model.dto

import com.example.android_task.model.simple.Film
import com.google.gson.annotations.SerializedName


data class ListFilmsResponseDto(
    @SerializedName("docs")
    val list: List<Film>
)
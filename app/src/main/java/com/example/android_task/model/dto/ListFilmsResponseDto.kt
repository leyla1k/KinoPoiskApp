package com.example.android_task.model.dto

import com.example.android_task.model.simple.Film
import com.google.gson.annotations.SerializedName


data class ListFilmsResponseDto (
//    @SerializedName("status")
//    val status: String?,//не факт что стринг
    @SerializedName("docs")
    val list: List<Film>
)
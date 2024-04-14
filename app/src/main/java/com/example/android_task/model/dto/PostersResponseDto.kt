package com.example.android_task.model.dto

import com.example.android_task.model.simple.Poster
import com.google.gson.annotations.SerializedName

data class PostersResponseDto(
    @SerializedName("docs")
    val urls: List<Poster>
)
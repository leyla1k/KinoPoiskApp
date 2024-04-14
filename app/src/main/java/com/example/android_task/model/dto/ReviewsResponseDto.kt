package com.example.android_task.model.dto

import com.example.android_task.model.simple.Poster
import com.example.android_task.model.simple.Review
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class ReviewsResponseDto(
    @SerializedName("docs")
    val reviews: List<Review>
)



package com.example.android_task.model.dto

import com.example.android_task.model.simple.Review
import com.google.gson.annotations.SerializedName

data class ReviewsResponseDto(
    @SerializedName("docs")
    val reviews: List<Review>
)



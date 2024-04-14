package com.example.android_task.network

import com.example.android_task.model.dto.FilmResponseDto
import com.example.android_task.model.dto.ListFilmsResponseDto
import com.example.android_task.model.dto.PostersResponseDto
import com.example.android_task.model.dto.ReviewsResponseDto
import com.example.android_task.utils.Global
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Global.END_POINT)
    suspend fun getFilmsList(
        @Query("page") page: Int
    ): ListFilmsResponseDto

    @GET("${Global.END_POINT}/{id}")//"${Constants.END_POINT}/{id}"
    suspend fun getFilmById(
        @Path("id") id: Int
    ): FilmResponseDto


    @GET(Global.END_POINT)
    suspend fun getFilmsListByFilter(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
        @Query("year") year: List<String>?,
        @Query("ageRating") ageRating: List<String>?,
        @Query("countries.name") countries: List<String>?,
    ): ListFilmsResponseDto
//жанр


    @GET(Global.END_POINT_SEARCH)
    suspend fun getFilmsByName(
        @Query("page") page: Int,
        @Query("query") query: String,
    ): ListFilmsResponseDto


    @GET("v1.4/movie?page=1&limit=10&year=2020&ageRating=12")
    suspend fun getTest(
    ): ListFilmsResponseDto



    @GET(Global.END_POINT_IMAGE)
    suspend fun getPosters(
        @Query("page") page: Int,
        @Query("selectFields") url: String = "url",
        @Query("movieId") movieId: Int,

    ): PostersResponseDto


    @GET(Global.END_POINT_REVIEW)
    suspend fun getReviews(
        @Query("page") page: Int,
        @Query("selectFields") title: String,
        @Query("selectFields") type: String,
        @Query("selectFields") review: String,
        @Query("movieId") movieId: Int,
    ): ReviewsResponseDto




}
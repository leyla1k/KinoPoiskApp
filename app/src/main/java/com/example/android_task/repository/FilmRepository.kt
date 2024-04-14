package com.example.android_task.repository


import com.example.android_task.model.dto.ReviewsResponseDto
import com.example.android_task.network.ApiService
import com.example.android_task.utils.Global
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getFilmsList(page: Int) = api.getFilmsList(page)
    suspend fun getFilmById(id: Int) = api.getFilmById(id)

    suspend fun getFilmsByName(
        page: Int,
        query: String,
    ) = api.getFilmsByName(page, query)

    suspend fun getTest(
        page: Int,
        year: List<String>,
        ageRating: List<String>,
        countries: List<String>
    ) = api.getTest()

    suspend fun getFilmsListByFilter(
        page: Int,
        year: List<String>?,
        ageRating: List<String>?,
        countries: List<String>?
    ) = api.getFilmsListByFilter(
        page, 10,
        year, ageRating, countries
    )

    suspend fun getPosters(
        page: Int,
        movieId: Int,
    ) = api.getPosters(
        page, "url", movieId,
    )


    suspend fun getReviews(
        page: Int,
        movieId: Int,
    ) = api.getReviews(page, "title","type","review",movieId)


}



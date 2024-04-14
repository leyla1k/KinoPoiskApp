package com.example.android_task.repository


import com.example.android_task.localdata.FilmDao
import com.example.android_task.model.FilmEntity
import com.example.android_task.network.ApiService
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: ApiService,
    private val dao: FilmDao,
) {
    suspend fun getFilmsList(page: Int) = api.getFilmsList(page)
    suspend fun getFilmById(id: Int) = api.getFilmById(id)

    suspend fun getFilmsByName(
        page: Int,
        query: String,
    ) = api.getFilmsByName(page, query)

    suspend fun getFilmsListByFilter(
        page: Int,
        year: List<String>?,
        ageRating: List<String>?,
        countries: List<String>?,
        isSeries: Boolean?,
        genre: List<String>?
    ) = api.getFilmsListByFilter(
        page, 10,
        year, ageRating, countries, isSeries, genre
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
    ) = api.getReviews(page, "title", "type", "review", movieId)

    suspend fun getFilmsListFromLocal(page: Int) = dao.getFilmsListFromLocal()
    suspend fun insertAllFilmsToLocal(films: List<FilmEntity>) = dao.insertAllFilmsToLocal(films)

}



package com.example.android_task.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_task.enums.RequestAction
import com.example.android_task.model.simple.Film
import com.example.android_task.model.dto.ListFilmsResponseDto
import com.example.android_task.repository.FilmRepository
import com.example.android_task.model.simple.Filter
import retrofit2.HttpException

class FilmPaginationSource(
    private val repository: FilmRepository,
    var requestAction: RequestAction = RequestAction.GET_FILMS_LIST,
    var data: Filter? = null,
    var query: String? = null,
) : PagingSource<Int, Film>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val currentPage = params.key ?: 1

            Log.d(
                "FilmPaginationSource",
                "load: ${this} } ${requestAction}   }"
            )
            //  lateinit var response: ListFilmsResponseDto

            val response = networkRequest(currentPage)

            if (response.list.isEmpty()) {
                return LoadResult.Error(Exception())
            }

            //add data in list
            LoadResult.Page(
                data = response.list,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            //internet filer exception

            //выполение из рума?

            LoadResult.Error(e)
        } catch (e: HttpException) {
            //server exception
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return 1
    }

        suspend fun networkRequest(currentPage: Int): ListFilmsResponseDto {
            lateinit var response: ListFilmsResponseDto
            when (requestAction) {
                RequestAction.GET_FILMS_LIST -> {
                    response = repository.getFilmsList(currentPage)
                }

                RequestAction.GET_FILMS_LIST_BY_FILTER -> {
                    response = repository.getFilmsListByFilter(
                        currentPage,
                        data!!.listYears,
                        data!!.listAgeRating,
                        data!!.listCountries,
                        data!!.isSeries,
                        data!!.listGenres,
                    )
                }

                RequestAction.GET_FILMS_BY_NAME -> {
                    response = repository.getFilmsByName(currentPage, query!!)

                }

                else -> println("x не соответствует ни одному из условий")
            }

            return response
        }
    }
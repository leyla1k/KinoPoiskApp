package com.example.android_task.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_task.model.simple.Review
import com.example.android_task.repository.FilmRepository
import retrofit2.HttpException

class ReviewPaginationSource(
    private val repository: FilmRepository,
    var movieId: Int,
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getReviews(currentPage, movieId)

            LoadResult.Page(
                data = response.reviews,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }
}
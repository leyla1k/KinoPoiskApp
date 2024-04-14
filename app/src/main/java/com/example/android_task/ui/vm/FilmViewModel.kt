package com.example.android_task.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android_task.enums.RequestAction
import com.example.android_task.model.simple.Film
import com.example.android_task.model.simple.Poster
import com.example.android_task.model.simple.Review
import com.example.android_task.pagination.FilmPaginationSource
import com.example.android_task.pagination.ReviewPaginationSource
import com.example.android_task.repository.FilmRepository
import com.example.android_task.utils.toFilm
import com.example.android_task.utils.toPosters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {


    lateinit var film: Film
    lateinit var reviewList: Flow<PagingData<Review>>
    lateinit var reviewPaginationSource :ReviewPaginationSource


    suspend fun getFilmById(id: Int): Film {
        film = repository.getFilmById(id).toFilm()

         reviewList = Pager(PagingConfig(1)) {
            reviewPaginationSource = ReviewPaginationSource(repository, id)
            reviewPaginationSource
        }.flow.cachedIn(viewModelScope)

        return film
    }


    suspend fun getPosters(): List<Poster> {
        return repository.getPosters(1, movieId = film.id).toPosters()
    }


}
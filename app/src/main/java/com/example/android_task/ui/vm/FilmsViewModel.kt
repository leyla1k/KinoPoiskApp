package com.example.android_task.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android_task.enums.RequestAction
import com.example.android_task.pagination.FilmPaginationSource
import com.example.android_task.repository.FilmRepository
import com.example.android_task.model.simple.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {
    val TAG = "FilmsViewModel"
    var filmPaginationSource = FilmPaginationSource(repository)
    var requestAction = RequestAction.GET_FILMS_LIST
    var query:String? = null
    var data:Filter? = null


    var filmList = Pager(PagingConfig(1)) {
        filmPaginationSource = FilmPaginationSource(repository)
        filmPaginationSource.requestAction = this.requestAction
        filmPaginationSource.query = this.query
        filmPaginationSource.data = this.data
        filmPaginationSource
    }.flow.cachedIn(viewModelScope)

    fun getFilms(){
        this.requestAction = RequestAction.GET_FILMS_LIST
        filmPaginationSource.invalidate()
    }

    fun getFilmsByName(query: String) {
        this.query = query
        this.requestAction = RequestAction.GET_FILMS_BY_NAME
        filmPaginationSource.invalidate()
    }

    fun getNewListWithFilters(data: Filter) {
        this.data = data
        this.requestAction = RequestAction.GET_FILMS_LIST_BY_FILTER
        filmPaginationSource.invalidate()
    }
}




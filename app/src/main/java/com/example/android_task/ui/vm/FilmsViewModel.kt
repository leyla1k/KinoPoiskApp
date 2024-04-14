package com.example.android_task.ui.vm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android_task.enums.RequestAction
import com.example.android_task.model.simple.Filter
import com.example.android_task.pagination.FilmPaginationSource
import com.example.android_task.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {
    val TAG = "FilmsViewModel"
    var filmPaginationSource = FilmPaginationSource(repository)
    var requestAction = RequestAction.GET_FILMS_LIST
    var query: String? = null
    var data: Filter? = null
    var isConnected: Boolean? = null

    var filmList = Pager(PagingConfig(1)) {
        filmPaginationSource = FilmPaginationSource(repository)
        filmPaginationSource.requestAction = this.requestAction
        filmPaginationSource.query = this.query
        filmPaginationSource.data = this.data
        filmPaginationSource.isConnected = this.isConnected
        filmPaginationSource
    }.flow.cachedIn(viewModelScope)

    fun getFilms() {
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


    fun Context.networkStateFlow(): Flow<Boolean> = callbackFlow {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                /*  val isConnected = isNetworkConnected(context)
                  offer(isConnected)*/
            }
        }
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
        awaitClose { unregisterReceiver(receiver) }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager != null) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false
    }

}




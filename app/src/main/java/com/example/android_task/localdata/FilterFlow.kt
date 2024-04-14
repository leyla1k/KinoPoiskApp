package com.example.android_task.localdata


import com.example.android_task.model.simple.Filter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object FilterFlow {

    //var filt
    private val _filterFlow = MutableStateFlow<Filter?>(null)
    val filterFlow: StateFlow<Filter?> = _filterFlow

    fun sendData(filter: Filter) {
        _filterFlow.value = filter
    }



}
package com.github.ahmadriza.mvvmboilerplate.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.github.ahmadriza.mvvmboilerplate.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

/*
* Perform get saved data then update it the remote
* */

fun <T, A> performLazyGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> performOperation(
    operation: suspend () -> Resource<T>, saveResult: suspend (T) -> Unit, delay: Long = 0L
): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
    emit(Resource.loading())
    val responseStatus = operation.invoke()
    delay(delay)
    if (responseStatus.status == SUCCESS) {
        emit(responseStatus)
        saveResult.invoke(responseStatus.data!!)
    } else if (responseStatus.status == ERROR) {
        emit(Resource.error(responseStatus.message!!))
    }
}






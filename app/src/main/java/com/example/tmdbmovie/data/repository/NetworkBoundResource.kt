package com.example.tmdbmovie.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.tmdbmovie.data.model.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType> {

    @WorkerThread
    protected abstract suspend fun callApi() : Response<RequestType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType) : Boolean

    @MainThread
    protected abstract fun loadFromDb() : Flow<ResultType>

    @WorkerThread
    protected abstract suspend fun saveToDb(items: RequestType)

    protected open fun onFetchFailed() {}

    @ExperimentalCoroutinesApi
    fun asFlow() : Flow<Resource<ResultType>> = flow {

        emit(Resource.loading())

        emit(Resource.success(loadFromDb().first()))

        if (shouldFetch(loadFromDb().first())) {
            val apiResponse = callApi()
            val moviesList = apiResponse.body()

            if (apiResponse.isSuccessful && moviesList != null) {
                saveToDb(moviesList)
            } else {
                emit(Resource.error<ResultType>(apiResponse.message()))
            }
        }

        emitAll(loadFromDb().map {
            Resource.success(it)
        })

    }.catch { e ->
        emit(Resource.error("Network Error. Cannot get API Response"))
        e.printStackTrace()
    }

}
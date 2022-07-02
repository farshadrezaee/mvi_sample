package com.example.mvisample.data.repository

import com.example.mvisample.base.ApiService
import com.example.mvisample.base.Failure
import com.example.mvisample.base.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

open class BaseRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    suspend fun <T : Any> fetchData(requestFunc: suspend ApiService.() -> Response<T>) = flow {
        try {
            val response = requestFunc.invoke(apiService)

            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
                return@flow
            }

            emit(Resource.Error(Failure("", response.code())))

        } catch (exception: Exception) {
            emit(Resource.Error(Failure(exception.message ?: "")))
        }

    }

}

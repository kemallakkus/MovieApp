package com.example.movieapp.common.base

import com.example.movieapp.common.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T : Any> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiToBeCalled.invoke()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Resource.Success(body)
                    } else {
                        Resource.Error("Empty response body", response.code())
                    }
                } else {
                    handleErrorResponse(response)
                }

            } catch (throwable: Throwable) {
                Resource.Error(throwable.message ?: "Unknown error", null, throwable)
            }
        }
    }

    private fun <T> handleErrorResponse(response: Response<T>): Resource.Error {
        val errorBody = response.errorBody()?.string()
        val errorMessage = when (response.code()) {
            400 -> "Bad Request: $errorBody"
            401 -> "Unauthorized: $errorBody"
            403 -> "Forbidden: $errorBody"
            404 -> "Not Found: $errorBody"
            500 -> "Internal Server Error: $errorBody"
            else -> "Unknown error: $errorBody"
        }
        return Resource.Error(errorMessage, response.code())
    }
}

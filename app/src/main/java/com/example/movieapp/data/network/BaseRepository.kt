package com.example.movieapp.data.network

import com.example.movieapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T : Any> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiToBeCalled.invoke()

                if (response.isSuccessful) {
                    Resource.Success(response.body() ?: return@withContext Resource.Error("Something went wrong"))
                } else {
                    Resource.Error(response.message())
                }

            } catch (throwable: Throwable) {
                Resource.Error(throwable.message ?: "")
            }
        }
    }
}
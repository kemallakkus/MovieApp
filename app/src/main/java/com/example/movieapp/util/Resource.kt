package com.example.movieapp.util

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val message: Int) : Resource<Nothing>()
}

suspend fun <T : Any, N : Any> Resource<T>.onSuccess(data: suspend (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> {
            Resource.Success(data(this.data))
        }

        is Resource.Error -> Resource.Error(this.message)
    }
}
package com.example.movieapp.util

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
}

fun Resource.Error.toPagingException(): PagingException {
    return PagingException(error)
}

suspend fun <T : Any, N : Any> Resource<T>.onSuccess(data: suspend (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> {
            Resource.Success(data(this.data))
        }

        is Resource.Error -> Resource.Error(this.error)
    }
}


suspend fun <T : Any, N : Any> Resource<T>.map(data: suspend (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> Resource.Error(this.error)
    }
}

suspend fun <T : Any> Resource<T>.onFailure(failure: suspend (String) -> Unit): Resource<T> {
    when (this) {
        is Resource.Success -> this.data
        is Resource.Error -> failure(this.error)
    }
    return this
}
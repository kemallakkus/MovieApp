package com.example.movieapp.common.util

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: String, val code: Int? = null, val throwable: Throwable? = null) : Resource<Nothing>()
}

/**
 * Bu fonksiyon, bir Resource.Error nesnesini PagingException nesnesine dönüştürür.
 * Hata mesajını PagingException içine taşır.
 */
fun Resource.Error.toPagingException(): PagingException {
    return PagingException(error = error)
}

/**
 * API'den gelen sonuçları domain modeline dönüştürür.
 */
suspend fun <T : Any, N : Any> Resource<T>.transform(data: suspend (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> {
            try {
                Resource.Success(data(this.data))
            } catch (e: Throwable) {
                Resource.Error(e.message ?: "Unknown error")
            }
        }

        is Resource.Error -> this
    }
}
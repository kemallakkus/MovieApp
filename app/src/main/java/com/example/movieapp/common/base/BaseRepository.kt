package com.example.movieapp.common.base

import com.example.movieapp.common.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * BaseRepository sınıfı, API çağrılarını güvenli bir şekilde gerçekleştirmek ve
 * oluşabilecek hataları yönetmek için kullanılan soyut bir sınıftır. Bu sınıf,
 * API çağrıları sırasında oluşabilecek yaygın hataları ele alır ve çağrıları
 * asenkron olarak yönetir.
 *
 * Yöntemler:
 * - safeApiCall: Genel API çağrılarını güvenli bir şekilde gerçekleştirmek için
 *   kullanılan bir yöntemdir. Başarılı olursa Resource.Success, hata oluşursa
 *   Resource.Error döner.
 * - handleErrorResponse: Hata yanıtlarını yönetir ve uygun hata mesajlarını
 *   oluşturur. HTTP durum kodlarına göre farklı hata mesajları döner.
 *
 * @param T : API çağrısından beklenen veri türüdür.
 */
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
            401 -> "Unauthorized: saddasdasdasdasd"
            403 -> "Forbidden: $errorBody"
            404 -> "Not Found: $errorBody"
            500 -> "Internal Server Error: $errorBody"
            else -> "Unknown error: $errorBody"
        }
        return Resource.Error(errorMessage, response.code())
    }
}

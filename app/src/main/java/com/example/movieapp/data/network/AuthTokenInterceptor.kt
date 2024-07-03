package com.example.movieapp.data.network

import android.content.SharedPreferences
import android.util.Log
import com.example.movieapp.BuildConfig
import com.example.movieapp.common.extentions.containsAuthEndpoints
import com.example.movieapp.common.util.Constants
import com.example.movieapp.common.util.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        val requestBuilder = originalRequest.newBuilder()

        if (url.containsAuthEndpoints()) {
            // Authentication işlemleri sırasında request token kullanılır
            requestBuilder.header("Authorization", "Bearer ${BuildConfig.API_KEY}")
        } else {
            // Diğer tüm isteklerde API anahtarı ve oturum belirleyicisi kullanılır
            val urlWithApiKey = originalRequest.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            requestBuilder.url(urlWithApiKey)

            val sessionId = sharedPreferences.getString(Constants.SESSION_ID_KEY, null)
            sessionId?.let {
                requestBuilder.header("Authorization", "Bearer $it")
            }
        }

        val request = requestBuilder.build()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            // Hata günlüğünü ekleyin
            Log.e("AuthTokenInterceptor", "Request failed with code: ${response.code}")
        }

        return response
    }
}



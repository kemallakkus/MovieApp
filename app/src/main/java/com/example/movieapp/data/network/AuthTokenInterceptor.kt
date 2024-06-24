package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.common.util.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TOKEN = BuildConfig.API_KEY
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
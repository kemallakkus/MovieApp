package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.common.util.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//bi interceptor oluşturduk. istek atılmadan önce retrtofitin ne yapmsaı gerektiğini söyledik.

//class AuthTokenInterceptor @Inject constructor() : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        TOKEN = BuildConfig.API_KEY
//        val original = chain.request()
//        val requestBuilder = original.newBuilder()
//            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
//        val request = requestBuilder.build()
//        return chain.proceed(request)
//    }
//}

class AuthTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
package com.example.movieapp.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.AuthTokenInterceptor
import com.example.movieapp.data.source.remote.MovieService
import com.example.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authTokenInterceptor: AuthTokenInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(authTokenInterceptor)
            readTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideAuthTokenInterceptor(): AuthTokenInterceptor = AuthTokenInterceptor()
}

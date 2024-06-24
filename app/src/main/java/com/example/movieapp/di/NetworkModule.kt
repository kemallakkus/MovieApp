package com.example.movieapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movieapp.BuildConfig.BASE_URL
import com.example.movieapp.data.network.AuthTokenInterceptor
import com.example.movieapp.data.source.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authTokenInterceptor: AuthTokenInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(authTokenInterceptor)
            addInterceptor(chuckerInterceptor)
            readTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(
        okHttpClient: OkHttpClient
    ): MovieService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideAuthTokenInterceptor(): AuthTokenInterceptor = AuthTokenInterceptor()
}

package com.example.movieapp.data.source.remote

import com.example.movieapp.data.dto.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/tv")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<MovieDto>
}
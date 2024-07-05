package com.example.movieapp.data.source

import com.example.movieapp.data.dto.request.SessionRequest
import com.example.movieapp.data.dto.response.DetailDto
import com.example.movieapp.data.dto.response.GenresDto
import com.example.movieapp.data.dto.response.MovieDto
import com.example.movieapp.data.dto.response.SessionDto
import com.example.movieapp.data.dto.response.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("discover/tv")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Response<MovieDto>

    @GET("tv/{series_id}")
    suspend fun getDetail(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String = "en-US",
    ): Response<DetailDto>

    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String,
    ): Response<TokenDto>

    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") apiKey: String,
        @Body sessionRequest: SessionRequest,
    ): Response<SessionDto>

    @GET("genre/tv/list")
    suspend fun getGenres(
        @Query("language") language: String = "en-US",
    ): Response<GenresDto>

}
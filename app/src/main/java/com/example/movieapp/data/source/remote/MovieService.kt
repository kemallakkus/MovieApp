package com.example.movieapp.data.source.remote

import com.example.movieapp.data.dto.MovieDto
import com.example.movieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query ("api_key") apiKey: String = API_KEY,
        @Query ("page") page: Int = 1,
        @Query("sources") sources: String,
    ): MovieDto
}
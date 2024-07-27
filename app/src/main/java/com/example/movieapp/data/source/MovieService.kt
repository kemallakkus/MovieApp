package com.example.movieapp.data.source

import com.example.movieapp.common.util.APIConst.CREATE_SESSION
import com.example.movieapp.common.util.APIConst.CREATE_TOKEN
import com.example.movieapp.common.util.APIConst.GET_DETAIL
import com.example.movieapp.common.util.APIConst.GET_GENRES
import com.example.movieapp.common.util.APIConst.GET_ME
import com.example.movieapp.common.util.APIConst.GET_MOVIES
import com.example.movieapp.common.util.APIConst.VALIDATE_TOKEN
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.data.dto.request.SessionRequest
import com.example.movieapp.data.dto.response.AccountDto
import com.example.movieapp.data.dto.response.DetailDto
import com.example.movieapp.data.dto.response.GenresDto
import com.example.movieapp.data.dto.response.MovieDto
import com.example.movieapp.data.dto.response.SessionDto
import com.example.movieapp.data.dto.response.TokenDto
import com.example.movieapp.data.dto.response.ValidateTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(GET_MOVIES)
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Response<MovieDto>

    @GET(GET_DETAIL)
    suspend fun getDetail(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String = "en-US",
    ): Response<DetailDto>

    @GET(CREATE_TOKEN)
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String,
    ): Response<TokenDto>

    @POST(VALIDATE_TOKEN)
    suspend fun validateToken(
        @Body loginRequest: LoginRequest,
    ): Response<ValidateTokenDto>

    @POST(CREATE_SESSION)
    suspend fun createSession(
        @Query("api_key") apiKey: String,
        @Body sessionRequest: SessionRequest,
    ): Response<SessionDto>

    @GET(GET_GENRES)
    suspend fun getGenres(
        @Query("language") language: String = "en-US",
    ): Response<GenresDto>

    @GET(GET_ME)
    suspend fun getMe(
        @Path("account_id") accountId: Int,
    ): Response<AccountDto>
}
package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.common.util.Resource
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.domain.model.Account
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.Session
import com.example.movieapp.domain.model.Token
import com.example.movieapp.domain.model.ValidateToken
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getDetail(id: Int): Resource<Detail>
    suspend fun createRequestToken(): Resource<Token>
    suspend fun createSession(requestToken: String): Resource<Session>
    suspend fun getGenres(): Resource<Genres>
    suspend fun validateToken(loginRequest: LoginRequest): Resource<ValidateToken>
    suspend fun getMe(accountId: Int): Resource<Account>
}
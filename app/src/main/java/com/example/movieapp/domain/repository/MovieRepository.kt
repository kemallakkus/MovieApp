package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.common.util.Resource
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.Session
import com.example.movieapp.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getDetail(id: Int): Resource<Detail>
    suspend fun createRequestToken(): Resource<Token>
    suspend fun createSession(requestToken: String): Resource<Session>
    suspend fun getGenres(): Resource<List<Genre>>
}
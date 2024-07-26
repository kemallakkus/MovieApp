package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.common.base.BaseRepository
import com.example.movieapp.common.util.Resource
import com.example.movieapp.data.source.MovieService
import com.example.movieapp.common.base.safeApiCallPaging
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.common.util.transform
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.data.dto.request.SessionRequest
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.model.Session
import com.example.movieapp.domain.model.Token
import com.example.movieapp.domain.model.ValidateToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : BaseRepository(), MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return safeApiCallPaging { page, _ ->
            safeApiCall {
                movieService.getMovies(page = page)
            }.transform { result ->
                result.results.toDomain()
            }
        }
    }

    override suspend fun getDetail(id: Int): Resource<Detail> {
        return safeApiCall {
            movieService.getDetail(id)
        }.transform { result ->
            result.toDomain()
        }
    }

    override suspend fun createRequestToken(): Resource<Token> {
        return safeApiCall {
            movieService.createRequestToken(BuildConfig.API_KEY)
        }.transform {
            it.toDomain()
        }
    }

    override suspend fun validateToken(loginRequest: LoginRequest): Resource<ValidateToken> {
        return safeApiCall {
            movieService.validateToken(loginRequest)
        }.transform {
            it.toDomain()
        }
    }

    override suspend fun createSession(requestToken: String): Resource<Session> {
        return safeApiCall {
            movieService.createSession(BuildConfig.API_KEY, SessionRequest(requestToken))
        }.transform {
            it.toDomain()
        }
    }

    override suspend fun getGenres(): Resource<Genres> {
        return safeApiCall {
            movieService.getGenres()
        }.transform {
            it.toDomain()
        }
    }
}
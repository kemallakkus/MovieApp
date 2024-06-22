package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.common.base.BaseRepository
import com.example.movieapp.data.source.remote.MovieService
import com.example.movieapp.data.source.remote.safeApiCallPaging
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.common.util.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : BaseRepository(), MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return safeApiCallPaging { page, _ ->
            safeApiCall {
                movieService.getMovies(page = page)
            }.map { result ->
                result.results.toDomain()
            }
        }
    }

    override suspend fun getDetail(id: Int): Detail {
        return movieService.getDetail(id).toDomain()
    }
}
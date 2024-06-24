package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.common.base.BaseRepository
import com.example.movieapp.common.util.Resource
import com.example.movieapp.data.source.MovieService
import com.example.movieapp.common.base.safeApiCallPaging
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.common.util.transform
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
}
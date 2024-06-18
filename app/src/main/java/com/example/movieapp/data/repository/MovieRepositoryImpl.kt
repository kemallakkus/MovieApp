package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.network.BaseRepository
import com.example.movieapp.data.source.remote.MovieService
import com.example.movieapp.data.source.remote.safeApiCallPaging
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.util.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : BaseRepository(), MoviesRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return safeApiCallPaging { page, _ ->
            safeApiCall {
                movieService.getMovies(page = page)
            }.map {
                it.results.toDomain()
            }
        }
    }
}


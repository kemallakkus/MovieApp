package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.data.source.remote.MovieService
import com.example.movieapp.data.source.remote.MoviesPagingSource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : MoviesRepository {

    override suspend fun getMovies(sources: List<String>): Flow<PagingData<ResultDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory =
            {
                MoviesPagingSource(
                    moviesApi = movieService,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}


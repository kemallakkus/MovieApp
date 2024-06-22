package com.example.movieapp.domain.usecases

import androidx.paging.PagingData
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MovieRepository,
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return moviesRepository.getMovies()
    }
}
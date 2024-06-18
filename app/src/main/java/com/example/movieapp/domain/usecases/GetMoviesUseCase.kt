package com.example.movieapp.domain.usecases

import androidx.paging.PagingData
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return moviesRepository.getMovies()
    }
}
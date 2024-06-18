package com.example.movieapp.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(source: List<String>) = flow {
        emit(
            moviesRepository.getMovies(sources = source).map {
                it.map { movies ->
                    movies.toDomain()
                }
            }
        )
    }
}
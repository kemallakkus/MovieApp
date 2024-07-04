package com.example.movieapp.domain.usecases

import com.example.movieapp.common.util.transform
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = flow {
        emit(
            movieRepository.getGenres().transform { it }
        )
    }
}
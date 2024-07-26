package com.example.movieapp.domain.usecases

import com.example.movieapp.common.util.transform
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(requestToken: String) = flow {
        emit(
            movieRepository.createSession(requestToken).transform {
                it
            }
        )
    }
}
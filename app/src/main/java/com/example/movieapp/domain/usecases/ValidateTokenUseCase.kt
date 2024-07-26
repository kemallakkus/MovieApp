package com.example.movieapp.domain.usecases

import com.example.movieapp.common.util.transform
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateTokenUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(loginRequest: LoginRequest) = flow {
        emit(
            movieRepository.validateToken(loginRequest).transform {
                it
            }
        )
    }
}
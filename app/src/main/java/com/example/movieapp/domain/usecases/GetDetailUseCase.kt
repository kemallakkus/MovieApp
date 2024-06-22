package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val moviesRepository: MovieRepository,
) {

    suspend operator fun invoke(id: Int): Detail {
        return moviesRepository.getDetail(id)
    }
}
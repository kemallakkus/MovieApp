package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(id: Int): Detail {
        return moviesRepository.getDetail(id)
    }
}
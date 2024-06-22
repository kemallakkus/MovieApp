package com.example.movieapp.domain.usecases

import com.example.movieapp.common.util.onSuccess
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val moviesRepository: MovieRepository,
) {

    operator fun invoke(id: Int) = flow {
        emit(
            moviesRepository.getDetail(id).onSuccess {
                it
            }
        )
    }

//    suspend operator fun invoke(id: Int): Detail {
//        return moviesRepository.getDetail(id)
//    }
}
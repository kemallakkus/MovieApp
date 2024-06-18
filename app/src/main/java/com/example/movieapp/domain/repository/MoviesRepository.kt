package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(sources: List<String>): Flow<PagingData<ResultDto>>
}
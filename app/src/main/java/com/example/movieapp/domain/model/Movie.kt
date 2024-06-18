package com.example.movieapp.domain.model

import com.example.movieapp.util.Constants.EMPTY_STRING
import com.example.movieapp.util.Constants.ZERO_DOUBLE
import com.example.movieapp.util.Constants.ZERO_FLOAT
import com.example.movieapp.util.Constants.ZERO_INT

data class Movie(
    val backdropPath: String = EMPTY_STRING,
    val genreIds: List<Int> = emptyList(),
    val id: Int = ZERO_INT,
    val originalLanguage: String = EMPTY_STRING,
    val originalTitle: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val popularity: Double = ZERO_DOUBLE,
    val posterPath: String = EMPTY_STRING,
    val releaseDate: String = EMPTY_STRING,
    val title: String = EMPTY_STRING,
    val voteAverage: Float = ZERO_FLOAT,
    val voteAverageFormat: String = EMPTY_STRING,
    val voteCount: Int = ZERO_INT
)

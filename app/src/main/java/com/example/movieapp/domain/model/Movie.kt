package com.example.movieapp.domain.model

import com.example.movieapp.util.Constants.EMPTY_STRING
import com.example.movieapp.util.Constants.ZERO_DOUBLE
import com.example.movieapp.util.Constants.ZERO_FLOAT
import com.example.movieapp.util.Constants.ZERO_INT

data class Movie(
    val id: Int = ZERO_INT,
    val name: String = EMPTY_STRING,
    val originalLanguage: String = EMPTY_STRING,
    val originalName: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val popularity: Double = ZERO_DOUBLE,
    val backdropPath: String = EMPTY_STRING,
    val firstAirDate: String = EMPTY_STRING,
    val genreIds: List<Int> = emptyList(),
    val originCountry: List<String> = emptyList(),
    val posterPath: String = EMPTY_STRING,
    val voteAverage: Float = ZERO_FLOAT,
    val voteAverageFormat: String = EMPTY_STRING,
    val voteCount: Int = ZERO_INT
)

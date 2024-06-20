package com.example.movieapp.domain.model

import com.example.movieapp.util.Constants.EMPTY_STRING
import com.example.movieapp.util.Constants.ZERO_DOUBLE
import com.example.movieapp.util.Constants.ZERO_FLOAT
import com.example.movieapp.util.Constants.ZERO_INT

data class Movie(
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val posterPath: String,
    val voteAverage: Float,
    val voteAverageFormat: String,
    val voteCount: Int
)

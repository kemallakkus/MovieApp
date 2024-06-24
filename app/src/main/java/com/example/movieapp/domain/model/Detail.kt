package com.example.movieapp.domain.model

import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.common.util.Constants.ZERO_DOUBLE
import com.example.movieapp.common.util.Constants.ZERO_FLOAT
import com.example.movieapp.common.util.Constants.ZERO_INT

data class Detail(
    val backdropPath: String = EMPTY_STRING,
    val id: Int = ZERO_INT,
    val name: String = EMPTY_STRING,
    val numberOfEpisodes: Int = ZERO_INT,
    val numberOfSeasons: Int = ZERO_INT,
    val originalLanguage: String = EMPTY_STRING,
    val originalName: String = EMPTY_STRING,
    val firstAirDate: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val popularity: Double = ZERO_DOUBLE,
    val posterPath: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val voteAverage: Float = ZERO_FLOAT,
    val voteAverageFormat: String = EMPTY_STRING,
    val createdBy: List<CreatedBy> = emptyList()
)
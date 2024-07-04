package com.example.movieapp.domain.model

import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.common.util.Constants.ZERO_INT

data class Genres(
    val genres: List<Genre> = emptyList()
)

data class Genre(
    val id: Int = ZERO_INT,
    val name: String = EMPTY_STRING
)
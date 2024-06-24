package com.example.movieapp.domain.model

import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.common.util.Constants.ZERO_INT

data class CreatedBy(
    val creditId: String = EMPTY_STRING,
    val gender: Int = ZERO_INT,
    val id: Int = ZERO_INT,
    val name: String = "Director",
    val profilePath: String = EMPTY_STRING,
)

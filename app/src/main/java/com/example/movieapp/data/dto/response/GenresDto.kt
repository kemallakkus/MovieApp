package com.example.movieapp.data.dto.response

data class GenresDto(
    val genres: List<GenreDto>? = null
)

data class GenreDto(
    val id: Int? = null,
    val name: String? = null
)
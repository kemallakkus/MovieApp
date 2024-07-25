package com.example.movieapp.data.mapper

import com.example.movieapp.common.util.orZero
import com.example.movieapp.data.dto.response.GenreDto
import com.example.movieapp.data.dto.response.GenresDto
import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.model.Genres

fun GenresDto.toDomain() = Genres(
    genres = genres?.map { it.toGenre() }.orEmpty()
)

private fun GenreDto?.toGenre() = Genre(
    id = this?.id.orZero(),
    name = this?.name.orEmpty()
)
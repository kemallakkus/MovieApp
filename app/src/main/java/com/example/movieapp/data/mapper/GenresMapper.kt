package com.example.movieapp.data.mapper

import com.example.movieapp.common.util.orZero
import com.example.movieapp.data.dto.response.GenreDto
import com.example.movieapp.domain.model.Genre

fun List<GenreDto>?.toDomain() = this?.map { dto ->
    Genre(
        id = dto.id.orZero(),
        name = dto.name.orEmpty()
    )
}.orEmpty()
package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.response.SessionDto
import com.example.movieapp.domain.model.Session

fun SessionDto.toDomain() = Session(
    sessionId = sessionId.orEmpty(),
    success = success ?: false
)

package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.response.TokenDto
import com.example.movieapp.domain.model.Token

fun TokenDto.toDomain() = Token(
    requestToken = requestToken.orEmpty(),
    expiresAt = expiresAt.orEmpty(),
    success = success ?: false
)
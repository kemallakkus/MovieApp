package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.response.TokenDto
import com.example.movieapp.data.dto.response.ValidateTokenDto
import com.example.movieapp.domain.model.Token
import com.example.movieapp.domain.model.ValidateToken

fun ValidateTokenDto.toDomain() = ValidateToken(
    requestToken = requestToken.orEmpty(),
    expiresAt = expiresAt.orEmpty(),
    success = success ?: false
)
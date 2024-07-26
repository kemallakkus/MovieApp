package com.example.movieapp.domain.model

data class ValidateToken (
    val requestToken: String,
    val expiresAt: String,
    val success: Boolean? = false
)
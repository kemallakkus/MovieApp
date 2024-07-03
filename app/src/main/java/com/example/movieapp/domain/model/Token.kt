package com.example.movieapp.domain.model

data class Token (
    val requestToken: String,
    val expiresAt: String,
    val success: Boolean? = false
)
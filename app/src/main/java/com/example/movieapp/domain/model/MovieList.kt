package com.example.movieapp.domain.model

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
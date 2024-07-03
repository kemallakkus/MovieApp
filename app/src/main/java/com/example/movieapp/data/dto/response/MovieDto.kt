package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val page: Int? = null,
    val results: List<ResultDto>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
)
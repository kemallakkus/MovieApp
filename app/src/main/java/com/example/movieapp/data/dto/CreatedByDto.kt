package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName

data class CreatedByDto(
    @SerializedName("credit_id")
    val creditId: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
)
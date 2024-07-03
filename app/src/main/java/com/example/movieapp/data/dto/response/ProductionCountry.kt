package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String? = null,
    val name: String? = null,
)
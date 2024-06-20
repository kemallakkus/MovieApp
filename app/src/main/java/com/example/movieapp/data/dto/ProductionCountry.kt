package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String? = null,
    val name: String? = null,
)
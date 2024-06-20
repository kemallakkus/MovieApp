package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ProductionCompany(
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null,
)
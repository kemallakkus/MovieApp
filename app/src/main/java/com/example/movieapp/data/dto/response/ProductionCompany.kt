package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null,
)
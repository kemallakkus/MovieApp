package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso: String? = null,
    val name: String? = null,
)
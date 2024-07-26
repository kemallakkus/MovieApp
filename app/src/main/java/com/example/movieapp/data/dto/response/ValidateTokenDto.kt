package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class ValidateTokenDto(
    @SerializedName("request_token")
    val requestToken: String? = null,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("expires_at")
    val expiresAt: String? = null
)
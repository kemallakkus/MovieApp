package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class SessionDto(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("session_id")
    val sessionId: String? = null
)
package com.example.movieapp.data.dto.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    val password: String,
    @SerializedName("request_token")
    val requestToken: String,
)
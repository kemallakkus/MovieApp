package com.example.movieapp.data.dto.request

import com.google.gson.annotations.SerializedName

data class SessionRequest(
    @SerializedName("request_token") val requestToken: String
)
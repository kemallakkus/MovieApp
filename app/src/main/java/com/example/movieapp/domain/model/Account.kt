package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Account(
    val avatar: Avatar,
    val id: Int,
    val includeAdult: Boolean,
    val iso31661: String,
    val iso6391: String,
    val name: String,
    val username: String,
)

data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb,
)

data class Gravatar(
    val hash: String,
)

data class Tmdb(
    val avatarPath: String,
)
package com.example.movieapp.data.dto.response

import com.google.gson.annotations.SerializedName

data class AccountDto(
    val avatar: AvatarDto? = null,
    val id: Int? = null,
    @SerializedName("include_adult")
    val includeAdult: Boolean? = null,
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    val name: String? = null,
    val username: String? = null,
)

data class AvatarDto(
    val gravatar: GravatarDto? = null,
    val tmdb: TmdbDto? = null,
)

data class GravatarDto(
    val hash: String? = null,
)

data class TmdbDto(
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
)
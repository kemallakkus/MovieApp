package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("air_date")
    val airDate: Any? = null,
    @SerializedName("episode_count")
    val episodeCount: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)
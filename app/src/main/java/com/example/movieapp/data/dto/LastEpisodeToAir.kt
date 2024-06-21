package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName


data class LastEpisodeToAir(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_number")
    val episodeNumber: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("production_code")
    val productionCode: String? = null,
    val runtime: Int? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("show_id")
    val showId: Int? = null,
    @SerializedName("still_path")
    val stillPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
)
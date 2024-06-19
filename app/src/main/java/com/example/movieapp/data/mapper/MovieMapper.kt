package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.util.APIConst.IMAGE_URL
import com.example.movieapp.util.orZero
import java.util.Locale

fun List<ResultDto>?.toDomain() = this?.map {
    Movie(
        id = it.id.orZero(),
        name = it.name.orEmpty(),
        originalLanguage = it.originalLanguage.orEmpty(),
        originalName = it.originalName.orEmpty(),
        overview = it.overview.orEmpty(),
        popularity = it.popularity.orZero(),
        backdropPath = it.backdropPath.orEmpty(),
        firstAirDate = it.firstAirDate.orEmpty(),
        genreIds = it.genreIds.orEmpty(),
        originCountry = it.originCountry.orEmpty(),
        posterPath = IMAGE_URL + it.posterPath.orEmpty(),
        voteAverage = it.voteAverage?.toFloat().orZero(),
        voteAverageFormat = String.format(Locale.US, "%.1f", it.voteAverage.orZero()),
        voteCount = it.voteCount.orZero()
    )
}.orEmpty()
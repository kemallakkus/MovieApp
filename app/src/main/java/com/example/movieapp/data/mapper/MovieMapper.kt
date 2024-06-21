package com.example.movieapp.data.mapper

import android.util.Log
import com.example.movieapp.data.dto.ResultDto
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.common.util.APIConst.IMAGE_URL
import com.example.movieapp.common.util.orZero
import java.util.Locale

fun List<ResultDto>?.toDomain() = this?.map { dto ->
    Log.d("Mapper", "Mapping ResultDto: $dto")
    Movie(
        id = dto.id.orZero(),
        name = dto.name.orEmpty(),
        originalLanguage = dto.originalLanguage.orEmpty(),
        originalName = dto.originalName.orEmpty(),
        overview = dto.overview.orEmpty(),
        popularity = dto.popularity.orZero(),
        backdropPath = IMAGE_URL + dto.backdropPath.orEmpty(),
        firstAirDate = dto.firstAirDate.orEmpty(),
        genreIds = dto.genreIds.orEmpty(),
        originCountry = dto.originCountry.orEmpty(),
        posterPath = getImageUrl(dto.posterPath.orEmpty()),
        voteAverage = dto.voteAverage?.toFloat().orZero(),
        voteAverageFormat = String.format(Locale.US, "%.1f", dto.voteAverage.orZero()) + " %",
        voteCount = dto.voteCount.orZero()
    )
}.orEmpty()


private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"
package com.example.movieapp.data.mapper

import com.example.movieapp.data.dto.DetailDto
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.common.util.APIConst.IMAGE_URL
import com.example.movieapp.common.util.APIConst.ORIGINAL_IMAGE_URL
import com.example.movieapp.common.util.orZero
import com.example.movieapp.data.dto.CreatedByDto
import com.example.movieapp.domain.model.CreatedBy
import java.util.Locale

fun DetailDto.toDomain() = Detail(
    backdropPath = ORIGINAL_IMAGE_URL + backdropPath.orEmpty(),
    id = id.orZero(),
    name = name.orEmpty(),
    numberOfEpisodes = numberOfEpisodes.orZero(),
    numberOfSeasons = numberOfSeasons.orZero(),
    originalLanguage = originalLanguage.orEmpty(),
    firstAirDate = firstAirDate.orEmpty(),
    originalName = originalName.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = IMAGE_URL + posterPath.orEmpty(),
    status = status.orEmpty(),
    voteAverage = voteAverage?.toFloat().orZero(),
    voteAverageFormat = String.format(Locale.US, "%.1f", voteAverage.orZero()),
    createdBy = createdBy?.map { it.toDomain() }.orEmpty()
)

fun CreatedByDto.toDomain() = CreatedBy(
    creditId = creditId.orEmpty(),
    gender = gender.orZero(),
    id = id.orZero(),
    name = name.orEmpty(),
    profilePath = IMAGE_URL +profilePath.orEmpty()
)
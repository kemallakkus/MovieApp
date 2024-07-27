package com.example.movieapp.data.mapper

import com.example.movieapp.common.util.orFalse
import com.example.movieapp.common.util.orZero
import com.example.movieapp.data.dto.response.AccountDto
import com.example.movieapp.data.dto.response.AvatarDto
import com.example.movieapp.data.dto.response.GravatarDto
import com.example.movieapp.data.dto.response.TmdbDto
import com.example.movieapp.domain.model.Account
import com.example.movieapp.domain.model.Avatar
import com.example.movieapp.domain.model.Gravatar
import com.example.movieapp.domain.model.Tmdb

fun AccountDto.toAccount() = Account(
    avatar = avatar.toAvatar(),
    id = id.orZero(),
    includeAdult = includeAdult.orFalse(),
    iso31661 = iso31661.orEmpty(),
    iso6391 = iso6391.orEmpty(),
    name = name.orEmpty(),
    username = username.orEmpty()
)

private fun AvatarDto?.toAvatar() = Avatar(
    gravatar = this?.gravatar.toGravatar(),
    tmdb = this?.tmdb.toTmdb()
)

private fun GravatarDto?.toGravatar() = Gravatar(
    hash = this?.hash.orEmpty()
)

private fun TmdbDto?.toTmdb() = Tmdb(
    avatarPath = this?.avatarPath.orEmpty()
)
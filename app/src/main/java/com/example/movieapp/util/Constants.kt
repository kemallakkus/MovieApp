package com.example.movieapp.util

import com.example.movieapp.util.Constants.ZERO_DOUBLE
import com.example.movieapp.util.Constants.ZERO_FLOAT
import com.example.movieapp.util.Constants.ZERO_INT

object Constants {

    var TOKEN = ""

    const val BASE_URL = "https://api.themoviedb.org/"

    const val API_KEY = "327115d62c1938f5194a8aa089d38286"

    const val USER_SETTINGS = "userSettings"

    const val APP_ENTRY = "appEntry"

    const val EMPTY_STRING = ""

    const val ZERO_DOUBLE = 0.0

    const val ZERO_INT = 0

    const val ZERO_FLOAT = 0f
}

fun Int?.orZero() = this ?: ZERO_INT
fun Float?.orZero() = this ?: ZERO_FLOAT
fun Double?.orZero() = this ?: ZERO_DOUBLE
fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true
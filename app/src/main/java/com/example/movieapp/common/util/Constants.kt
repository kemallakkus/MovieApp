package com.example.movieapp.common.util

import com.example.movieapp.common.util.Constants.ZERO_DOUBLE
import com.example.movieapp.common.util.Constants.ZERO_FLOAT
import com.example.movieapp.common.util.Constants.ZERO_INT

object Constants {

    @Volatile
    var TOKEN = ""

    const val USER_SETTINGS = "userSettings"

    const val APP_ENTRY = "appEntry"

    const val EMPTY_STRING = ""

    const val ZERO_DOUBLE = 0.0

    const val ZERO_INT = 0

    const val ZERO_FLOAT = 0f
}

fun Int?.orZero() = this ?: 0
fun Double?.orZero() = this ?: 0.0
fun Float?.orZero() = this ?: 0f
fun String?.orEmpty() = this ?: ""
fun <T> List<T>?.orEmpty() = this ?: emptyList()
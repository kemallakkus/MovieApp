package com.example.movieapp.common.util

object Constants {

    @Volatile
    var TOKEN = ""

    const val EMPTY_STRING = ""

    const val ZERO_DOUBLE = 0.0

    const val ZERO_INT = 0

    const val ZERO_FLOAT = 0f

    const val SESSION_ID_KEY = "SESSION_ID"
}

fun Int?.orZero() = this ?: 0
fun Double?.orZero() = this ?: 0.0
fun Float?.orZero() = this ?: 0f
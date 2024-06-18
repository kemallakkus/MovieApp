package com.example.movieapp.util

import com.example.movieapp.util.Constants.EMPTY_STRING

class PagingException(
    val error: String = EMPTY_STRING,
    val resError: Int = 0
) : Exception()
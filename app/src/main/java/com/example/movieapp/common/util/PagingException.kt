package com.example.movieapp.common.util

import com.example.movieapp.common.util.Constants.EMPTY_STRING

class PagingException(
    val error: String = EMPTY_STRING,
) : Exception()
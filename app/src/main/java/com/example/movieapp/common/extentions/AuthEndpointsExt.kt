package com.example.movieapp.common.extentions

fun String.containsAuthEndpoints(): Boolean {
    return this.contains("authentication/token/new") || this.contains("authentication/session/new")
}
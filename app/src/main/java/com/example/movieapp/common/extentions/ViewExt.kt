package com.example.movieapp.common.extentions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(
    url: String?,
    isCircleCrop: Boolean = false,
    errorImage: Int? = null
) {
    val skipMemoryCache = RequestOptions().skipMemoryCache(false)

    val circleCrop = if (isCircleCrop) RequestOptions().circleCrop() else RequestOptions()

    Glide.with(context)
        .load(url)
        .apply(skipMemoryCache)
        .error(errorImage)
        .apply(circleCrop)
        .into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * asdasd
 */
fun View.gone() {
    visibility = View.GONE
}
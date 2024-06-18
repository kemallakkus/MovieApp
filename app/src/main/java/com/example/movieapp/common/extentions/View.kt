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

fun View.gone() {
    visibility = View.GONE
}

fun setViewsVisible(vararg views: View) = views.forEach {
    it.visible()
}

fun setViewsGone(vararg views: View) = views.forEach {
    it.gone()
}
package com.example.movieapp.common.extentions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * ImageView uzantı fonksiyonu, belirtilen URL'den bir görüntü yükler.
 * Ayrıca bir hata durumunda gösterilecek bir varsayılan
 * hata görüntüsü belirtebilirsiniz.
 *
 * @param url Görüntünün yüklenmesi gereken URL.
 * @param errorImage Hata durumunda gösterilecek varsayılan hata görüntüsü.
 */
fun ImageView.loadImage(
    url: String?,
    isCircleCrop: Boolean = false,
    errorImage: Int? = null,
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

/**
 * View uzantı fonksiyonu, görünürlüğü VISIBLE olarak ayarlar.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * View uzantı fonksiyonu, görünürlüğü GONE olarak ayarlar.
 */
fun View.gone() {
    visibility = View.GONE
}
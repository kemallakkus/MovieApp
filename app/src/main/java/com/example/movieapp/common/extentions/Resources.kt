package com.example.movieapp.common.extentions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

fun Context.resString(@StringRes stringRes: Int) = this.resources.getString(stringRes)

fun Fragment.resString(@StringRes stringRes: Int) = this.resources.getString(stringRes)

fun Context.resColor(@ColorRes colorRes: Int) =
    ResourcesCompat.getColor(resources, colorRes, null)

fun Fragment.resColor(@ColorRes colorRes: Int) =
    ResourcesCompat.getColor(resources, colorRes, null)

fun Context.resDrawable(@DrawableRes drawableRes: Int) =
    ResourcesCompat.getDrawable(resources, drawableRes, null)

fun Context.getFont(font: Int) = ResourcesCompat.getFont(this, font)

fun TextView.setFont(font: Int) {
    typeface = ResourcesCompat.getFont(this.context, font)
}

fun EditText.setFont(font: Int) {
    typeface = ResourcesCompat.getFont(this.context, font)
}

fun Context.getDimenInt(@DimenRes resId: Int): Int {
    return resources.getDimensionPixelSize(resId)
}

fun ConstraintLayout.getDimenFloat(dimen: Int) = resources.getDimension(dimen)

fun View.setMargin(left: Int? = null, right: Int? = null, top: Int? = null, bottom: Int? = null) {
    val etInputParam = layoutParams as ViewGroup.MarginLayoutParams
    etInputParam.setMargins(
        left ?: this.left,
        top ?: this.top,
        right ?: this.right,
        bottom ?: this.bottom
    )
    layoutParams = etInputParam
    requestLayout()
}
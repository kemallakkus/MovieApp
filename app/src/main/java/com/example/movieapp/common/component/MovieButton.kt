package com.example.movieapp.common.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.movieapp.R
import com.example.movieapp.common.extentions.getDimenInt
import com.example.movieapp.common.extentions.setFont
import com.example.movieapp.databinding.ComponentButtonBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MovieButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ComponentButtonBinding = ComponentButtonBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    private var text = "Button"
    private var icon = 0
    private var iconColor = 0
    private var radius = 0
    private var borderColor = 0
    private var borderWidth = 0
    private var textColor = 0
    private var fontType = FontType.REGULAR
    private var width = 0
    private var height = 0

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MovieButton).apply {
            try {
                text = getString(R.styleable.MovieButton_mb_text) ?: ""
                icon = getResourceId(R.styleable.MovieButton_mb_icon, 0)
                iconColor = getColor(R.styleable.MovieButton_mb_iconColor, 0)
                radius = getDimensionPixelSize(R.styleable.MovieButton_mb_radius, 0)
                borderColor = getColor(R.styleable.MovieButton_mb_borderColor, 0)
                borderWidth = getDimensionPixelSize(R.styleable.MovieButton_mb_borderWidth, 0)
                textColor = getColor(R.styleable.MovieButton_mb_textColor, 0)
                width = getDimensionPixelSize(R.styleable.MovieButton_mb_width, 0)
                height = getDimensionPixelSize(R.styleable.MovieButton_mb_height, 0)
                fontType = FontType.values()[getInt(R.styleable.MovieButton_mb_font, 3)]

                setButtonText()
                setButtonWidthAndHeight()
                setTextFont()
                setTextColor()
                setButtonType(ButtonType.values()[getInt(R.styleable.MovieButton_mb_button_type, 0)])
            } finally {
                recycle()
            }
        }
    }

    private fun createShapeDrawable(): MaterialShapeDrawable {
        return MaterialShapeDrawable().apply {
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, radius.toFloat())
                .build()
        }
    }

    fun setButtonText(text: String? = null) {
        binding.tvName.text = text ?: this.text
        binding.tvName.visibility = View.VISIBLE
    }

    private fun setTextFont() {
        binding.tvName.setFont(
            when (fontType) {
                FontType.LIGHT -> R.font.worksans_light
                FontType.REGULAR -> R.font.worksans_regular
                FontType.MEDIUM -> R.font.worksans_medium
                FontType.SEMIBOLD -> R.font.worksans_semibold
            }
        )
    }

    private fun setTextColor() {
        if (textColor != 0) binding.tvName.setTextColor(textColor)
    }

    private fun setButtonWidthAndHeight() {
        if (width == 0) {
            binding.rlRoot.setPadding(
                context.getDimenInt(R.dimen.some_dimen),
                0,
                context.getDimenInt(R.dimen.some_dimen),
                0
            )
        }

        binding.rlRoot.layoutParams.height =
            context.getDimenInt(if (height != 0) height else R.dimen.default_height)
    }

    fun setButtonType(buttonType: ButtonType) {
        when (buttonType) {
            ButtonType.GREEN -> setType(R.color.green, R.color.white)
            ButtonType.PURPLE -> setType(R.color.purple, R.color.white)
            ButtonType.PURPLE_LIGHT -> setType(R.color.purple_lightest, R.color.purple)
            ButtonType.WHITE -> setType(R.color.white, R.color.gray90)
            ButtonType.BACKGROUND_WHITE -> setType(R.color.white, R.color.black)
            ButtonType.BLACK -> setType(R.color.black, R.color.white)
            ButtonType.GRAY -> setType(R.color.gray20, R.color.gray90)
        }
    }

    private fun setType(backgroundColorRes: Int, textColorRes: Int) {
        textColor = context.getColor(textColorRes)
        val shapeDrawable = createShapeDrawable()
        shapeDrawable.setTint(context.getColor(backgroundColorRes))

        if (borderWidth > 0) {
            shapeDrawable.strokeWidth = borderWidth.toFloat()
            shapeDrawable.strokeColor = context.getColorStateList(borderColor)
        }

        binding.tvName.setTextColor(textColor)
        binding.rlRoot.background = shapeDrawable
    }

    enum class FontType {
        LIGHT, REGULAR, MEDIUM, SEMIBOLD
    }

    enum class ButtonType {
        GREEN, PURPLE, WHITE, BACKGROUND_WHITE, BLACK, GRAY, PURPLE_LIGHT
    }
}



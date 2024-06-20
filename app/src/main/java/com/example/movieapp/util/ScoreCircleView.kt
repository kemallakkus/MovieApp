package com.example.movieapp.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ScoreCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var score: Float = 0f
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    private val rect = RectF()

    fun setScore(score: Float) {
        this.score = score
        invalidate() // Redraw the view
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val angle = (score / 10) * 360
        rect.set(
            paint.strokeWidth / 2,
            paint.strokeWidth / 2,
            width - paint.strokeWidth / 2,
            height - paint.strokeWidth / 2
        )

        paint.color = 0xFF00FF00.toInt() // Green color
        canvas.drawArc(rect, 270f, angle, false, paint)
    }
}

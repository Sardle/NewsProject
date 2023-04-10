package com.example.newsproject.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomProgressBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val animator = ObjectAnimator.ofFloat(this, ROTATION, 0f, 1000f).setDuration(1000)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    init {
        isClickable = true
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.BLACK
        canvas.drawRect(
            ((width / 2).toFloat()) - 100f,
            ((height / 2).toFloat()) - 85f,
            ((width / 2).toFloat()) + 100f,
            ((height / 2).toFloat()) + 85f,
            paint
        )
        animator.start()
    }
}
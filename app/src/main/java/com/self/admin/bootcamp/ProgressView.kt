package com.self.admin.bootcamp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        color = DEF_COLOR
        style = Paint.Style.STROKE
        strokeWidth = DEF_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(PADDING, PADDING, canvas?.width - PADDING, DEF_STROKE_WIDTH, 3f, 3f, paint)
    }

    companion object {
        private const val DEF_STROKE_WIDTH = 100f
        private const val DEF_COLOR = Color.GRAY
        private const val CIRCLE_RADIUS = 200f
        private const val PADDING = 120f
    }
}
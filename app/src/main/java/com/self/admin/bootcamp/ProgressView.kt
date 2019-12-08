package com.self.admin.bootcamp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.min


class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress: Float = 0f
        private set
    private lateinit var rectangle: RectF
    private lateinit var progressBar: RectF

    private val paint = Paint().apply {
        isAntiAlias = true
        color = DEF_COLOR
        style = Paint.Style.STROKE
        strokeWidth = DEF_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND

    }

    private val paintBG = Paint().apply {
        isAntiAlias = true
        color = DEF_BG_COLOR
        style = Paint.Style.STROKE
        strokeWidth = DEF_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND

    }


    init {
        context.takeIf { attrs != null }?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressView,
            defStyleAttr,
            0
        )?.apply {
            try {
                progress = getFloat(R.styleable.ProgressView_progress, DEF_PROGRESS)
                var strokeWidth =
                    getDimension(R.styleable.ProgressView_progressStrokeWidth, DEF_STROKE_WIDTH)
                paint.strokeWidth =
                    getDimension(R.styleable.ProgressView_progressStrokeWidth, DEF_STROKE_WIDTH)
                paint.color = getColor(R.styleable.ProgressView_progressColor, DEF_COLOR)
                paintBG.strokeWidth =
                    getDimension(R.styleable.ProgressView_progressStrokeWidth, DEF_STROKE_WIDTH)
                paintBG.color = getColor(R.styleable.ProgressView_backgroundColor, DEF_BG_COLOR)
                rectangle = RectF(
                    DEF_PADDING,
                    DEF_PADDING,
                    paintBG.strokeWidth * 10f + DEF_PADDING,
                    paintBG.strokeWidth + DEF_PADDING
                )
                progressBar = RectF(
                    DEF_PADDING,
                    DEF_PADDING,
                    (paintBG.strokeWidth * 10f + DEF_PADDING) * progress,
                    paintBG.strokeWidth + DEF_PADDING
                    )
            } finally {
                recycle()
            }
        }
    }

    fun increment(inc: Float?) {
        progress += inc ?: DEF_INC
        if (progress > DEF_MAX) progress = DEF_MAX
        invalidate()
    }


    fun decrement(dec: Float?) {
        progress -= dec ?: DEF_INC
        if (progress < DEF_MIN || (progress > DEF_MIN && progress < 0.01f)) {//Floating point ops
            progress = DEF_MIN
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth: Int = (paint.strokeWidth * 10f + DEF_PADDING * 2).toInt()
        val desiredHeight: Int = (paint.strokeWidth + DEF_PADDING * 2).toInt()
        setMeasuredDimension(
            measureDimension(desiredWidth, widthMeasureSpec),
            measureDimension(desiredHeight, heightMeasureSpec)
        )
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(
            rectangle,
            DEF_CURVE,
            DEF_CURVE,
            paintBG
        )
        if (progress != 0f) {
            canvas?.drawRoundRect(
                progressBar,
                DEF_CURVE,
                DEF_CURVE,
                paint
            )
        }
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (event.x >= left && event.x < right) {
                    progress = (event.x - left)/rectangle.width()
                    println(progress)
                    progressBar.right = event.x
                }
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    companion object {
        private const val DEF_STROKE_WIDTH = 100f
        private const val DEF_BG_COLOR = Color.GRAY
        private const val DEF_COLOR = Color.YELLOW
        private const val DEF_CURVE = 3f
        private const val DEF_PROGRESS = 0f
        private const val DEF_PADDING = 50f
        private const val DEF_INC = 0.20f
        private const val DEF_MAX = 1f
        private const val DEF_MIN = 0f
    }
}
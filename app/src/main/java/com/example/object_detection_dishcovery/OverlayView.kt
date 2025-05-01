package com.example.object_detection_dishcovery

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class OverlayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var results = listOf<BoundingBox>()
    private var boxPaint = Paint()
    private var textBackgroundPaint = Paint()
    private var textPaint = Paint()
    private var labelPaint = Paint()
    private var labelBackgroundPaint = Paint()

    private var bounds = Rect()

    init {
        initPaints()
    }

    fun clear() {
        textPaint.reset()
        textBackgroundPaint.reset()
        boxPaint.reset()
        labelPaint.reset()
        labelBackgroundPaint.reset()
        invalidate()
        initPaints()
    }

    private fun initPaints() {
        // Box outline
        boxPaint.color = ContextCompat.getColor(context!!, R.color.bounding_box_color)
        boxPaint.strokeWidth = 4F
        boxPaint.style = Paint.Style.STROKE

        // Inner label background
        textBackgroundPaint.color = Color.argb(200, 0, 0, 0) // Semi-transparent black
        textBackgroundPaint.style = Paint.Style.FILL

        // Inner label text
        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 40f
        textPaint.typeface = Typeface.DEFAULT_BOLD

        // Outer label background (shown outside the bounding box)
        labelBackgroundPaint.color = Color.WHITE
        labelBackgroundPaint.style = Paint.Style.FILL

        // Outer label text
        labelPaint.color = Color.BLACK
        labelPaint.style = Paint.Style.FILL
        labelPaint.textSize = 40f
        labelPaint.typeface = Typeface.DEFAULT_BOLD
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        results.forEach {
            val left = it.x1 * width
            val top = it.y1 * height
            val right = it.x2 * width
            val bottom = it.y2 * height

            // Draw bounding box outline
            canvas.drawRect(left, top, right, bottom, boxPaint)

            val drawableText = it.clsName

            // Draw outer label (below the box)
            textPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
            val textWidth = bounds.width()
            val textHeight = bounds.height()

            // Draw label tag at bottom of box
            val labelRect = RectF(
                left,
                bottom,
                left + textWidth + 2 * BOUNDING_RECT_TEXT_PADDING,
                bottom + textHeight + 2 * BOUNDING_RECT_TEXT_PADDING
            )

            canvas.drawRoundRect(labelRect, 8f, 8f, labelBackgroundPaint)
            canvas.drawText(
                drawableText,
                left + BOUNDING_RECT_TEXT_PADDING,
                bottom + textHeight + BOUNDING_RECT_TEXT_PADDING,
                labelPaint
            )
        }
    }

    fun setResults(boundingBoxes: List<BoundingBox>) {
        results = boundingBoxes
        invalidate()
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}
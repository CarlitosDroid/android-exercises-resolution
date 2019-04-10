package com.example.android_exercises_resolution

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Compass : View {

    private var direction: Float = 0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            View.MeasureSpec.getSize(widthMeasureSpec),
            View.MeasureSpec.getSize(heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {

        val w = measuredWidth
        val h = measuredHeight
        val r: Int
        if (w > h) {
            r = h / 2
        } else {
            r = w / 2
        }

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.color = Color.WHITE

        canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), r.toFloat(), paint)

        paint.color = Color.RED
        canvas.drawLine(
            (w / 2).toFloat(),
            (h / 2).toFloat(),
            (w / 2 + r * Math.sin((-direction).toDouble())).toFloat(),
            (h / 2 - r * Math.cos((-direction).toDouble())).toFloat(),
            paint
        )

    }

    fun update(dir: Float) {
        direction = dir
        invalidate()
    }
}
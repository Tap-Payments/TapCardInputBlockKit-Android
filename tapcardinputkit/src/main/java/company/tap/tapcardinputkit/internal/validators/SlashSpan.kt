package company.tap.tapcardinputkit.internal.validators

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.style.ReplacementSpan

/**
 * Created by AhlaamK on 4/17/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
class SlashSpan : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        val padding = paint.measureText(" ", 0, 1) * 2
        val slash = paint.measureText("/", 0, 1)
        val textSize = paint.measureText(text, start, end)
        return (padding + slash + textSize).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        canvas.drawText(text!!.subSequence(start, end).toString() + " / ", x, y.toFloat(), paint)

    }

}
package com.edwardharker.kge.util

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align.*

// TODO change to a simple object pool and use Paint#reset
object PaintCache {
    private val paints = mutableMapOf<PaintData, Paint>()

    fun get(
        color: Int = Color.BLACK,
        textSize: Float = 20f
    ): Paint {
        val paintData = PaintData(color, textSize)
        return paints[paintData] ?: Paint().apply {
            this.color = paintData.color
            this.textSize = textSize
            this.textAlign = CENTER
        }
    }

    private data class PaintData(
        val color: Int,
        val textSize: Float
    )
}
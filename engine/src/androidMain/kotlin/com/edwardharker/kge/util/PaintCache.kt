package com.edwardharker.kge.util

import android.graphics.Color
import android.graphics.Paint

object PaintCache {
    private val paints = mutableMapOf<PaintData, Paint>()

    fun get(color: Int = Color.BLACK): Paint {
        val paintData = PaintData(color)
        return paints[paintData] ?: Paint().apply {
            this.color = paintData.color
        }
    }

    private data class PaintData(
        val color: Int
    )
}
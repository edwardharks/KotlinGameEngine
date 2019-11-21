package com.edwardharker.kge.util

import android.graphics.Paint
import java.util.*

object PaintObjectPool {
    private val paints = ArrayDeque<Paint>()

    fun use(block: (Paint) -> Unit) {
        returnToPool(get().also(block))
    }

    fun get(): Paint {
        return if (paints.peek() != null) paints.pop() else Paint()
    }

    fun returnToPool(paint: Paint) {
        paint.reset()
        paints.push(paint)
    }
}
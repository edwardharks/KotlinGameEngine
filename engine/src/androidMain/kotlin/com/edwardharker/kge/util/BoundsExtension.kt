package com.edwardharker.kge.util

import android.graphics.RectF

fun Bounds.toRect(): RectF {
    return RectF(
        this.left,
        -this.top,
        this.right,
        -this.bottom
    )
}

package com.edwardharker.kge.util

import android.graphics.Rect

fun Bounds.toRect(): Rect {
    return Rect(
        this.left.toInt(),
        -this.top.toInt(),
        this.right.toInt(),
        -this.bottom.toInt()
    )
}

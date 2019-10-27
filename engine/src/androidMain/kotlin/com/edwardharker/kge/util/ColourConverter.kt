package com.edwardharker.kge.util

import android.graphics.Color

fun Colour.toColor(): Int {
    return Color.argb(
        this.alpha,
        this.red,
        this.green,
        this.blue
    )
}

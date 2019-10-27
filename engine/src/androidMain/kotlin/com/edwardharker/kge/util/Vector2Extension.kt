package com.edwardharker.kge.util

fun Vector2.toAndroidCanvasSpace(): Vector2 {
    return Vector2(this.x, -this.y)
}

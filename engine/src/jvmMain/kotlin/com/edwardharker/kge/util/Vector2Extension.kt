package com.edwardharker.kge.util

fun Vector2.toGraphics2DSpace(): Vector2 {
    return Vector2(this.x, -this.y)
}

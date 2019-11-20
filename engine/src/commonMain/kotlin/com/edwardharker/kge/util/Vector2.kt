package com.edwardharker.kge.util

data class Vector2(var x: Float, var y: Float) {
    companion object {
        val ZERO: Vector2 = Vector2(0.0f, 0.0f)
    }
}

operator fun Vector2.times(value: Float): Vector2 {
    return Vector2(x = x * value, y = y * value)
}

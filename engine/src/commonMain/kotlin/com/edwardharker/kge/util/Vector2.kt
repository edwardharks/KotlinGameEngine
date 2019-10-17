package com.edwardharker.kge.util

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector2(val x: Float, val y: Float) {
    operator fun minus(other: Vector2): Vector2 {
        return Vector2(
            x = this.x - other.x,
            y = this.y - other.y
        )
    }

    companion object {
        val ZERO: Vector2 = Vector2(0.0f, 0.0f)

        fun dot(lhs: Vector2, rhs: Vector2): Float {
            return lhs.x * rhs.x + lhs.y * rhs.y
        }

        fun distance(from: Vector2, to: Vector2): Float {
            return sqrt((from.x - to.x).pow(2) + (from.y - to.y).pow(2))
        }
    }
}
package com.edwardharker.kge.util

import kotlin.math.max
import kotlin.math.min

data class Bounds(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float
)

val Bounds.width: Float
    get() = right - left

val Bounds.height: Float
    get() = top - bottom

val Bounds.center: Vector2
    get() = Vector2(
        x = left + width / 2,
        y = bottom + height / 2
    )

fun Bounds?.getIntersectingBounds(other: Bounds?): Bounds? {
    if (this == null || other == null) return null

    val left = max(left, other.left)
    val bottom = max(bottom, other.bottom)

    val right = min(right, other.right)
    val top = min(top, other.top)

    if (left > right || bottom > top) {
        return null
    }

    return Bounds(
        left = left,
        right = right,
        top = top,
        bottom = bottom
    )
}

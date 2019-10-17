package com.edwardharker.kge.component

import com.edwardharker.kge.util.Vector2

data class RectangleColliderComponent(
    val topLeft: Vector2,
    val topRight: Vector2,
    val bottomLeft: Vector2,
    val bottomRight: Vector2
) : ColliderComponent {
    constructor(
        width: Float,
        height: Float
    ) : this(Vector2.ZERO, Vector2(width, 0f), Vector2.ZERO, Vector2(width, height))

    override fun contains(point: Vector2): Boolean {
        val ab = bottomRight - bottomLeft
        val am = bottomLeft - point
        val bc = bottomLeft - topLeft
        val bm = bottomLeft - point
        val dotAbAm = Vector2.dot(ab, am)
        val dotAbAb = Vector2.dot(ab, ab)
        val dotBcBm = Vector2.dot(bc, bm)
        val dotBcBc = Vector2.dot(bc, bc)
        return dotAbAm in 0.0f..dotAbAb && dotBcBm in 0.0f..dotBcBc
    }
}
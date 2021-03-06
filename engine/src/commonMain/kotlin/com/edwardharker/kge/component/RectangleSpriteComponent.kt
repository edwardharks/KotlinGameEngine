package com.edwardharker.kge.component

import com.edwardharker.kge.util.Bounds
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

data class RectangleSpriteComponent(
    var width: Float,
    var height: Float,
    var colour: Colour,
    var cornerRadius: Float = 0f
) : Component

fun RectangleSpriteComponent.getBoundsAt(position: Vector2): Bounds {
    val halfWidth = width / 2
    val halfHeight = height / 2
    return Bounds(
        left = position.x - halfWidth,
        right = position.x + halfWidth,
        top = position.y + halfHeight,
        bottom = position.y - halfHeight
    )
}

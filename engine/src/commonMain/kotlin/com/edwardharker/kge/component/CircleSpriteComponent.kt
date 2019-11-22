package com.edwardharker.kge.component

import com.edwardharker.kge.util.Bounds
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

data class CircleSpriteComponent(
    var radius: Float,
    var colour: Colour
) : Component

fun CircleSpriteComponent.getBoundsAt(position: Vector2): Bounds {
    return Bounds(
        left = position.x - radius,
        right = position.x + radius,
        top = position.y + radius,
        bottom = position.y - radius
    )
}

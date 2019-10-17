package com.edwardharker.kge.component

import com.edwardharker.kge.util.Vector2

fun cameraComponent(
    position: Vector2 = Vector2.ZERO,
    aspectRatio: Float = 1f,
    size: Float = 200f
): List<Component> {
    return listOf(
        TransformComponent(
            position = position
        ),
        CameraComponent(
            aspectRatio = aspectRatio,
            size = size
        )
    )
}

package com.edwardharker.kge.component

import com.edwardharker.kge.util.Vector2

data class TransformComponent(
    val position: Vector2 = Vector2.ZERO
) : Component

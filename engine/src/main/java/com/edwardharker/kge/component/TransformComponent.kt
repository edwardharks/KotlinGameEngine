package com.edwardharker.kge.component

import com.edwardharker.kge.util.Rotation
import com.edwardharker.kge.util.Vector2

data class TransformComponent(
    var position: Vector2 = Vector2.ZERO,
    var rotation: Rotation = Rotation.ZERO
) : Component
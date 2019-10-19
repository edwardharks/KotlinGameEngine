package com.edwardharker.kge.component

import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.util.Bounds

data class CollisionComponent(
    val collisions: List<Collision> = emptyList()
) : Component

data class Collision(
    val other: Entity,
    val bounds: Bounds
)

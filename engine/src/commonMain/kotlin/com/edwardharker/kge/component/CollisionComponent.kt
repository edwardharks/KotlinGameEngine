package com.edwardharker.kge.component

import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.util.Bounds

data class CollisionComponent(
    var collisions: MutableList<Collision> = mutableListOf()
) : Component

data class Collision(
    var other: Entity,
    var bounds: Bounds
)

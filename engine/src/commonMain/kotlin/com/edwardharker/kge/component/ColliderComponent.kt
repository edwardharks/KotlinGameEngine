package com.edwardharker.kge.component

import com.edwardharker.kge.util.Vector2

interface ColliderComponent : Component {
    fun contains(point: Vector2): Boolean
}
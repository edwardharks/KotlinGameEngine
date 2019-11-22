package com.edwardharker.kge.component

import com.edwardharker.kge.util.Bounds

data class ColliderComponent(
    var bounds: Bounds? = null
) : Component

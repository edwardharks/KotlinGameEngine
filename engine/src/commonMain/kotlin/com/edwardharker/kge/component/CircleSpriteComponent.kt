package com.edwardharker.kge.component

import com.edwardharker.kge.util.Colour

data class CircleSpriteComponent(
    var radius: Float,
    var colour: Colour
) : Component

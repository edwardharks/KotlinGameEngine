package com.edwardharker.kge.component

import com.edwardharker.kge.util.Colour

data class RectangleSpriteComponent(
    val width: Float,
    val height: Float,
    val colour: Colour
) : Component
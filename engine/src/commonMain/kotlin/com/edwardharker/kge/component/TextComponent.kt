package com.edwardharker.kge.component

import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Colour.Companion.BLACK

data class TextComponent(
    val text: String = "",
    val size: Float = 20f,
    val colour: Colour = BLACK
) : Component

package com.edwardharker.kge.component

import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Colour.Companion.BLACK

data class TextComponent(
    var text: String = "",
    var size: Float = 20f,
    var colour: Colour = BLACK
) : Component

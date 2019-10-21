package com.edwardharker.kge.render

import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent

expect class TextRenderer() : Renderer {
    fun renderText(
        transform: TransformComponent,
        text: TextComponent
    )
}

package com.edwardharker.kge.render

import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent

actual class TextRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderText(
        transform: TransformComponent,
        text: TextComponent
    ) {
    }
}

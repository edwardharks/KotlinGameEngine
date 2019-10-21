package com.edwardharker.kge.render

import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import org.w3c.dom.HTMLCanvasElement

actual class TextRenderer actual constructor() : Renderer {
    override var htmlCanvas: HTMLCanvasElement? = null

    actual fun renderText(
        transform: TransformComponent,
        text: TextComponent
    ) {
        TODO()
    }
}

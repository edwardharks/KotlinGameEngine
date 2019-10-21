package com.edwardharker.kge.render

import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.create
import com.edwardharker.kge.util.toColor
import com.edwardharker.kge.util.toGraphics2DSpace
import java.awt.Graphics
import java.awt.RenderingHints.*


actual class TextRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderText(
        transform: TransformComponent,
        text: TextComponent
    ) = addRenderCommand { graphics: Graphics ->
        graphics.create {
            setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON)
            font = font.deriveFont(text.size)

            val width = fontMetrics.stringWidth(text.text)
            val height = fontMetrics.height

            val textGraphics2DPosition = transform.position.toGraphics2DSpace()
            val xPosition = textGraphics2DPosition.x - width / 2
            val yPosition = textGraphics2DPosition.y + height / 2

            color = text.colour.toColor()
            drawString(text.text, xPosition, yPosition)
        }
    }
}

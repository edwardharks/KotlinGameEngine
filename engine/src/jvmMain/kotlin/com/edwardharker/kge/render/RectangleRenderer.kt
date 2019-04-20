package com.edwardharker.kge.render

import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toColor
import java.awt.Graphics2D

actual class RectangleRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderRectangle(
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    ) {
        addRenderCommand { graphics: Graphics2D ->
            graphics.color = rectangle.colour.toColor()
            graphics.translate(
                (transform.position.x - rectangle.width / 2).toInt(),
                (transform.position.y - rectangle.height / 2).toInt()
            )
            graphics.rotate(
                transform.rotation.radians.toDouble(),
                (rectangle.width / 2).toDouble(),
                (rectangle.height / 2).toDouble()
            )
            graphics.fillRect(0, 0, rectangle.width.toInt(), rectangle.height.toInt())
        }
    }
}
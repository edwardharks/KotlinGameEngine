package com.edwardharker.kge.render

import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.create
import com.edwardharker.kge.util.toColor
import com.edwardharker.kge.util.toGraphics2DSpace
import java.awt.Graphics

actual class RectangleRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderRectangle(
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    ) {
        addRenderCommand { graphics: Graphics ->
            graphics.create {
                color = rectangle.colour.toColor()
                val rectGraphics2DPosition = transform.position.toGraphics2DSpace()
                val xPosition = rectGraphics2DPosition.x - rectangle.width / 2
                val yPosition = rectGraphics2DPosition.y - rectangle.height / 2
                translate(
                    xPosition.toInt(),
                    yPosition.toInt()
                )
                val cornerRadius = rectangle.cornerRadius.toInt()
                fillRoundRect(
                    0,
                    0,
                    rectangle.width.toInt(),
                    rectangle.height.toInt(),
                    cornerRadius,
                    cornerRadius
                )
            }
        }
    }
}

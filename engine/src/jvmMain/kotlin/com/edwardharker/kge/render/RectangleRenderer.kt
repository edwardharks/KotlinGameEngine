package com.edwardharker.kge.render

import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toColor
import com.edwardharker.kge.util.toGraphics2DSpace
import java.awt.Graphics2D

actual class RectangleRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderRectangle(
        cameraPosition: TransformComponent,
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    ) {
        addRenderCommand { graphics: Graphics2D ->
            graphics.color = rectangle.colour.toColor()
            val rectGraphics2DPosition = transform.position.toGraphics2DSpace()
            val cameraGraphics2DPosition = cameraPosition.position.toGraphics2DSpace()
            val xPosition = rectGraphics2DPosition.x - rectangle.width / 2
            val yPosition = rectGraphics2DPosition.y - rectangle.height / 2
            graphics.translate(
                (xPosition + cameraGraphics2DPosition.x).toInt(),
                (yPosition + cameraGraphics2DPosition.y).toInt()
            )
            graphics.fillRect(0, 0, rectangle.width.toInt(), rectangle.height.toInt())
        }
    }
}

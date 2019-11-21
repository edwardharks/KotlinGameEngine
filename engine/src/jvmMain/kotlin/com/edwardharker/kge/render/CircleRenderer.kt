package com.edwardharker.kge.render

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.create
import com.edwardharker.kge.util.toColor
import com.edwardharker.kge.util.toGraphics2DSpace
import java.awt.Graphics

actual class CircleRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderCircle(
        transform: TransformComponent,
        circle: CircleSpriteComponent
    ) {
        addRenderCommand { graphics: Graphics ->
            graphics.create {
                color = circle.colour.toColor()
                val rectGraphics2DPosition = transform.position.toGraphics2DSpace()


                val radius = circle.radius
                val xPosition = rectGraphics2DPosition.x - radius
                val yPosition = rectGraphics2DPosition.y - radius
                val diameter = (radius * 2).toInt()
                fillOval(xPosition.toInt(), yPosition.toInt(), diameter, diameter)
            }
        }
    }
}
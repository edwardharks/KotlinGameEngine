package com.edwardharker.kge.render

import android.graphics.Canvas
import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.PaintObjectPool
import com.edwardharker.kge.util.toColor

actual class CircleRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderCircle(
        transform: TransformComponent,
        circle: CircleSpriteComponent
    ) {
        addRenderCommand { canvas: Canvas ->
            PaintObjectPool.use { paint ->
                paint.color = circle.colour.toColor()
                canvas.drawCircle(transform.position.x, -transform.position.y, circle.radius, paint)
            }
        }
    }
}
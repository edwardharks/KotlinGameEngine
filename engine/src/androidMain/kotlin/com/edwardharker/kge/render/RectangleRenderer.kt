package com.edwardharker.kge.render

import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.util.PaintCache
import com.edwardharker.kge.util.toColor
import com.edwardharker.kge.util.toRect
import android.graphics.Canvas as AndroidCanvas

actual class RectangleRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderRectangle(
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    ) {
        addRenderCommand { canvas: AndroidCanvas ->
            val paint = PaintCache.get(
                color = rectangle.colour.toColor()
            )

            val rect = rectangle.getBoundsAt(transform.position).toRect()
            canvas.drawRect(rect, paint)
        }
    }
}
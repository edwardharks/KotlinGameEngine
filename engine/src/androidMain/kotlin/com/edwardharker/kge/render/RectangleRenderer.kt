package com.edwardharker.kge.render

import android.graphics.Paint
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
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
            val paint = Paint() // TODO avoid creating paint on every frame
            paint.color = rectangle.colour.toColor()

            val rect = rectangle.getBoundsAt(transform.position).toRect()
            println("Drawing rect: $rect")
            canvas.drawRect(rect, paint)
        }
    }
}
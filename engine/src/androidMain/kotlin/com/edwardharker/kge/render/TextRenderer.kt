package com.edwardharker.kge.render

import android.graphics.Canvas
import android.text.StaticLayout
import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.PaintCache
import com.edwardharker.kge.util.toAndroidCanvasSpace
import com.edwardharker.kge.util.toColor

actual class TextRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderText(
        transform: TransformComponent,
        text: TextComponent
    ) {
        addRenderCommand { canvas: Canvas ->
            val paint = PaintCache.get(
                color = text.colour.toColor(),
                textSize = text.size
            )

            val (x, y) = transform.position.toAndroidCanvasSpace()
            // TODO try StaticLayout which is possibly faster
            canvas.drawText(text.text, x, y + text.size / 2, paint)
        }
    }
}

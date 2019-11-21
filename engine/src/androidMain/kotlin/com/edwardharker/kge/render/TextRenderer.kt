package com.edwardharker.kge.render

import android.graphics.Canvas
import android.graphics.Paint.Align.CENTER
import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.PaintObjectPool
import com.edwardharker.kge.util.toAndroidCanvasSpace
import com.edwardharker.kge.util.toColor

actual class TextRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderText(
        transform: TransformComponent,
        text: TextComponent
    ) {
        addRenderCommand { canvas: Canvas ->
            PaintObjectPool.use { paint ->
                paint.apply {
                    color = text.colour.toColor()
                    textSize = text.size
                    textAlign = CENTER
                }

                val (x, y) = transform.position.toAndroidCanvasSpace()
                // TODO try StaticLayout which is possibly faster
                canvas.drawText(text.text, x, y + text.size / 2, paint)
            }
        }
    }
}

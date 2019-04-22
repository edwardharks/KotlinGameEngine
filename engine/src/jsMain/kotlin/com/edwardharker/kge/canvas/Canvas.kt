package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.Renderer
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document

actual class Canvas {
    private val htmlCanvas = document.getElementById("gameCanvas") as HTMLCanvasElement

    actual fun addRenderer(renderer: Renderer) {
        renderer.htmlCanvas = htmlCanvas
    }

    internal actual fun startRender() {
        val ctx = htmlCanvas.getContext("2d") as? CanvasRenderingContext2D
        ctx?.fillStyle = "black"
        ctx?.fillRect(
            x = 0.0,
            y = 0.0,
            w = htmlCanvas.width.toDouble(),
            h = htmlCanvas.height.toDouble()
        )
    }

    internal actual fun endRender() = Unit
}

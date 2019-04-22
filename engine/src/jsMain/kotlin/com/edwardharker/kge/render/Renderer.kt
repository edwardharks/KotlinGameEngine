package com.edwardharker.kge.render

import org.w3c.dom.HTMLCanvasElement

actual interface Renderer {
    var htmlCanvas: HTMLCanvasElement?
}
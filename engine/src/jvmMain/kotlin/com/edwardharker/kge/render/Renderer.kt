package com.edwardharker.kge.render

import java.awt.Graphics2D

typealias RenderCommand = (Graphics2D) -> Unit

actual interface Renderer {
    var addRenderCommand: (RenderCommand) -> Unit
}

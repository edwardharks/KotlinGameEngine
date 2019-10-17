package com.edwardharker.kge.render

import java.awt.Graphics

typealias RenderCommand = (Graphics) -> Unit

actual interface Renderer {
    var addRenderCommand: (RenderCommand) -> Unit
}

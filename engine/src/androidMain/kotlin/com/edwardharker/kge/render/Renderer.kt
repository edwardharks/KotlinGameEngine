package com.edwardharker.kge.render

import android.graphics.Canvas as AndroidCanvas

typealias RenderCommand = (AndroidCanvas) -> Unit

actual interface Renderer {
    var addRenderCommand: (RenderCommand) -> Unit
}

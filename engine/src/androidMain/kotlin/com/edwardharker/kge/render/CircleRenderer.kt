package com.edwardharker.kge.render

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.TransformComponent

actual class CircleRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderCircle(
        transform: TransformComponent,
        circle: CircleSpriteComponent
    ) {
        TODO()
    }
}
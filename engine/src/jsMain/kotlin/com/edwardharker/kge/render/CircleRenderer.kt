package com.edwardharker.kge.render

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import org.w3c.dom.HTMLCanvasElement

actual class CircleRenderer actual constructor() : Renderer {
    override var htmlCanvas: HTMLCanvasElement? = TODO()

    actual fun renderCircle(
        transform: TransformComponent,
        circle: CircleSpriteComponent
    ) {
        TODO()
    }
}

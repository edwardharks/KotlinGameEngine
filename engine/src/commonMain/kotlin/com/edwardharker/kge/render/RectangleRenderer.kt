package com.edwardharker.kge.render

import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent

expect class RectangleRenderer() : Renderer {
    fun renderRectangle(
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    )
}

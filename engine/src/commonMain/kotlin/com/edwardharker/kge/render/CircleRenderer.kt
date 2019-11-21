package com.edwardharker.kge.render

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent

expect class CircleRenderer() : Renderer {
    fun renderCircle(
        transform: TransformComponent,
        circle: CircleSpriteComponent
    )
}

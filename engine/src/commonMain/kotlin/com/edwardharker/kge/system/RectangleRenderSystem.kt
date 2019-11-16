package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.render.RectangleRenderer

class RectangleRenderSystem(
    private val rectangleRenderer: RectangleRenderer
) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents { _,
                                            transform: TransformComponent,
                                            rectangle: RectangleSpriteComponent ->
            rectangleRenderer.renderRectangle(transform, rectangle)
        }
    }
}

package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.render.RectangleRenderer

class RectangleRenderSystem(
    private val rectangleRenderer: RectangleRenderer
) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents(rectangleRenderer::renderRectangle)
    }
}

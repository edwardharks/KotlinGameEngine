package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.render.CircleRenderer
import com.edwardharker.kge.render.RectangleRenderer

class CircleRenderSystem(
    private val circleRenderer: CircleRenderer
) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents { _,
                                            transform: TransformComponent,
                                            circle: CircleSpriteComponent ->
            circleRenderer.renderCircle(transform, circle)
        }
    }
}

package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.render.RectangleRenderer

class RectangleRenderSystem(
    private val rectangleRenderer: RectangleRenderer
) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents { _: Entity,
                                            transform: TransformComponent,
                                            rectangle: RectangleSpriteComponent ->
            world.forEachEntityWithComponents { _: Entity,
                                                camera: CameraComponent,
                                                cameraPosition: TransformComponent ->
                rectangleRenderer.renderRectangle(cameraPosition, camera, transform, rectangle)
            }
        }
    }
}

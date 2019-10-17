package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.render.CameraRenderer

class CameraRenderSystem(private val cameraRenderer: CameraRenderer) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents { _: Entity,
                                            cameraPosition: TransformComponent,
                                            camera: CameraComponent ->
            cameraRenderer.renderCamera(cameraPosition, camera)
        }
    }
}

package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CameraComponent

object SingleCameraSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        val cameraComponents = world.getComponentsOfType(CameraComponent::class) ?: return
        check(cameraComponents.values.size <= 1) { "Only one camera is supported" }
    }
}
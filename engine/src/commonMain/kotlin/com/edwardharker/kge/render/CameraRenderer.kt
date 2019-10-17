package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent

expect class CameraRenderer() : Renderer {
    fun renderCamera(
        cameraPosition: TransformComponent,
        camera: CameraComponent
    )
}
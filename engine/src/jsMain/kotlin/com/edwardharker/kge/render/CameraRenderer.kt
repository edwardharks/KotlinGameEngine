package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import org.w3c.dom.HTMLCanvasElement

actual class CameraRenderer : Renderer {
    override var htmlCanvas: HTMLCanvasElement? = null

    actual fun renderCamera(
        cameraPosition: TransformComponent,
        camera: CameraComponent
    ) {
        TODO()
    }
}

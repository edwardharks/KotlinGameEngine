package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toAndroidCanvasSpace

actual class CameraRenderer actual constructor() : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderCamera(
        cameraPosition: TransformComponent,
        camera: CameraComponent
    ) {
        addRenderCommand { canvas ->
            val cameraGraphics2DPosition = cameraPosition.position.toAndroidCanvasSpace()
            val cameraHeight = 2 * camera.size
            val cameraWidth = cameraHeight * camera.aspectRatio
            canvas.clipRect(0f, 0f, cameraWidth, cameraHeight)

            canvas.translate(
                (-cameraGraphics2DPosition.x),
                (-cameraGraphics2DPosition.y)
            )
        }
    }
}
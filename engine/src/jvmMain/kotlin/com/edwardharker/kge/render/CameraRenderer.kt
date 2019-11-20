package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toGraphics2DSpace

actual class CameraRenderer : Renderer {
    override var addRenderCommand: (RenderCommand) -> Unit = {}

    actual fun renderCamera(
        cameraPosition: TransformComponent,
        camera: CameraComponent
    ) {
        addRenderCommand { graphics ->
            val cameraGraphics2DPosition = cameraPosition.position.toGraphics2DSpace()
            val cameraHeight = 2 * camera.size
            val cameraWidth = cameraHeight * camera.aspectRatio
            graphics.setClip(0, 0, cameraWidth.toInt(), cameraHeight.toInt())
            graphics.translate(
                (-cameraGraphics2DPosition.x + cameraWidth / 2).toInt(),
                (-cameraGraphics2DPosition.y + cameraHeight / 2).toInt()
            )
        }
    }
}

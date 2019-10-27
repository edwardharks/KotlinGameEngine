package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toAndroidCanvasSpace
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import kotlin.math.min


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

            val widthRatio = canvas.width / cameraWidth
            val heightRatio = canvas.height / cameraHeight
            val ratio = min(widthRatio, heightRatio)

            canvas.scale(ratio, ratio)

            canvas.clipRect(0f, 0f, cameraWidth, cameraHeight)

            canvas.translate(
                (-cameraGraphics2DPosition.x),
                (-cameraGraphics2DPosition.y)
            )
        }
    }
}
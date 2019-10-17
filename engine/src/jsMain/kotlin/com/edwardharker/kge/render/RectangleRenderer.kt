package com.edwardharker.kge.render

import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.toJsColor
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.CaretPosition
import org.w3c.dom.HTMLCanvasElement

actual class RectangleRenderer : Renderer {
    override var htmlCanvas: HTMLCanvasElement? = null

    actual fun renderRectangle(
        cameraPosition: TransformComponent,
        camera: CameraComponent,
        transform: TransformComponent,
        rectangle: RectangleSpriteComponent
    ) {
        TODO("I changed the coordinate system so this is now wrong. I also added the camera")
//        val ctx = htmlCanvas?.getContext("2d") as? CanvasRenderingContext2D
//        ctx?.fillStyle = rectangle.colour.toJsColor()
//
//        val rotatePivotX = (transform.position.x).toDouble()
//        val rotatePivotY = (transform.position.y).toDouble()
//
//        ctx?.translate(
//            rotatePivotX,
//            rotatePivotY
//        )
//        ctx?.rotate(transform.rotation.radians.toDouble())
//        ctx?.translate(
//            -rotatePivotX,
//            -rotatePivotY
//        )
//
//        val centerX = (transform.position.x - rectangle.width / 2).toDouble()
//        val centerY = transform.position.y - rectangle.height / 2.toDouble()
//
//        ctx?.fillRect(
//            x = centerX,
//            y = centerY,
//            w = rectangle.width.toDouble(),
//            h = rectangle.height.toDouble()
//        )
//
//        ctx?.translate(
//            rotatePivotX,
//            rotatePivotY
//        )
//        ctx?.rotate(-transform.rotation.radians.toDouble())
//        ctx?.translate(
//            -rotatePivotX,
//            -rotatePivotY
//        )
    }
}

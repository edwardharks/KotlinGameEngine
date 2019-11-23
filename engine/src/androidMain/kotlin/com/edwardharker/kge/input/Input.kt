package com.edwardharker.kge.input

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import com.edwardharker.kge.EntityWithComponent2
import com.edwardharker.kge.World
import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.util.toAndroidCanvasSpace
import kotlin.math.min

actual class Input actual constructor() : View.OnTouchListener, UpdateSystem {
    private var _primaryPointer: PointerAction = PointerAction.None
    actual val primaryPointer: PointerAction
        get() = synchronized(lock) { _primaryPointer }

    private val lock = Any()

    private var camera: EntityWithComponent2<CameraComponent, TransformComponent>? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        synchronized(lock) {
            val position = translateToGameCoordinates(v, event.x, event.y)
            _primaryPointer = if (position != null) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> PointerAction.Down(position)
                    MotionEvent.ACTION_MOVE -> PointerAction.Move(position)
                    MotionEvent.ACTION_UP -> PointerAction.Up(position)
                    else -> PointerAction.None
                }
            } else {
                PointerAction.None
            }
        }
        return true
    }

    override fun update(world: World, deltaTime: Float) {
        synchronized(lock) {
            camera = world
                .getEntitiesWithComponents(CameraComponent::class, TransformComponent::class)
                .firstOrNull()
        }
    }

    private fun translateToGameCoordinates(view: View, x: Float, y: Float): Vector2? {
        val (_, camera, transform) = this.camera ?: return null

        val cameraCanvasPosition = transform.position.toAndroidCanvasSpace()
        val cameraHeight = 2 * camera.size
        val cameraWidth = cameraHeight * camera.aspectRatio

        val widthRatio = view.width / cameraWidth
        val heightRatio = view.height / cameraHeight
        val ratio = min(widthRatio, heightRatio)

        val scaledX = x / ratio
        val scaledY = y / ratio

        return Vector2(
            x = scaledX - (-cameraCanvasPosition.x + cameraWidth / 2),
            y = (-cameraCanvasPosition.y + cameraHeight / 2) - scaledY
        )
    }
}

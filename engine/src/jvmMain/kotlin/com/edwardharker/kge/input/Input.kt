package com.edwardharker.kge.input

import com.edwardharker.kge.EntityWithComponent2
import com.edwardharker.kge.World
import com.edwardharker.kge.component.CameraComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.util.toGraphics2DSpace
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

actual class Input : MouseListener, MouseMotionListener, UpdateSystem {
    private var _primaryPointer: PointerAction = PointerAction.None
    actual val primaryPointer: PointerAction
        get() = synchronized(lock) { _primaryPointer }

    private val lock = Any()

    private var camera: EntityWithComponent2<CameraComponent, TransformComponent>? = null

    override fun update(world: World, deltaTime: Float) {
        synchronized(lock) {
            camera = world
                .getEntitiesWithComponents(CameraComponent::class, TransformComponent::class)
                .firstOrNull()
        }
    }

    override fun mouseReleased(e: MouseEvent) {
        synchronized(lock) {
            val position = translateToGameCoordinates(e.x, e.y)
            _primaryPointer = if (position != null) {
                PointerAction.Up(position)
            } else {
                PointerAction.None
            }
        }
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseClicked(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent) {
    }

    override fun mousePressed(e: MouseEvent) {
        synchronized(lock) {
            val position = translateToGameCoordinates(e.x, e.y)
            _primaryPointer = if (position != null) {
                PointerAction.Down(position)
            } else {
                PointerAction.None
            }
        }
    }

    override fun mouseMoved(e: MouseEvent) {
    }

    override fun mouseDragged(e: MouseEvent) {
        synchronized(lock) {
            val position = translateToGameCoordinates(e.x, e.y)
            _primaryPointer = if (position != null) {
                PointerAction.Move(position)
            } else {
                PointerAction.None
            }
        }
    }

    private fun translateToGameCoordinates(x: Int, y: Int): Vector2? {
        val (_, camera, transform) = this.camera ?: return null

        val cameraGraphics2DPosition = transform.position.toGraphics2DSpace()
        val cameraHeight = 2 * camera.size
        val cameraWidth = cameraHeight * camera.aspectRatio
        return Vector2(
            x = x - (-cameraGraphics2DPosition.x + cameraWidth / 2),
            y = (-cameraGraphics2DPosition.y + cameraHeight / 2) - y
        )
    }
}

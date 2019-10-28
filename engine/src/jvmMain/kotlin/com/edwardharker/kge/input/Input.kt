package com.edwardharker.kge.input

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

actual class Input : MouseListener, MouseMotionListener {
    private var _primaryPointer: PointerAction = PointerAction.None

    actual val primaryPointer: PointerAction
        get() = _primaryPointer

    override fun mouseReleased(e: MouseEvent) {
        // TODO translate x and y to game coordinates
        _primaryPointer = PointerAction.Up(e.x.toFloat(), e.y.toFloat())
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseClicked(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent) {
    }

    override fun mousePressed(e: MouseEvent) {
        // TODO translate x and y to game coordinates
        _primaryPointer = PointerAction.Down(e.x.toFloat(), e.y.toFloat())
    }

    override fun mouseMoved(e: MouseEvent) {
    }

    override fun mouseDragged(e: MouseEvent) {
        // TODO translate x and y to game coordinates
        _primaryPointer = PointerAction.Move(e.x.toFloat(), e.y.toFloat())
    }
}
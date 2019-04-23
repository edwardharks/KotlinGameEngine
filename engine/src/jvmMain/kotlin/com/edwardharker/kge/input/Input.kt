package com.edwardharker.kge.input

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

actual class Input : MouseListener, MouseMotionListener {
    private var _primaryPointer: PointerAction = PointerAction.None

    actual val primaryPointer: PointerAction
        get() = _primaryPointer

    override fun mouseReleased(e: MouseEvent) {
        _primaryPointer = PointerAction.Up(e.x.toFloat(), e.y.toFloat())
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseClicked(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent) {
    }

    override fun mousePressed(e: MouseEvent) {
        _primaryPointer = PointerAction.Down(e.x.toFloat(), e.y.toFloat())
    }

    override fun mouseMoved(e: MouseEvent) {
    }

    override fun mouseDragged(e: MouseEvent) {
        _primaryPointer = PointerAction.Move(e.x.toFloat(), e.y.toFloat())
    }
}
package com.edwardharker.kge.input

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

actual class Input actual constructor() : View.OnTouchListener {
    private var _primaryPointer: PointerAction = PointerAction.None
    actual val primaryPointer: PointerAction
        get() = _primaryPointer

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        // TODO translate x and y to game coordinates
        //  This will be tricky because the canvas is scaled to fit the screen
        val x = event.x
        val y = event.y
        _primaryPointer = when (event.action) {
            MotionEvent.ACTION_DOWN -> PointerAction.Down(x, y)
            MotionEvent.ACTION_MOVE -> PointerAction.Move(x, y)
            MotionEvent.ACTION_UP -> PointerAction.Up(x, y)
            else -> PointerAction.None
        }
        return true
    }
}

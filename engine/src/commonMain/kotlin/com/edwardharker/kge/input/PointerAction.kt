package com.edwardharker.kge.input

import com.edwardharker.kge.util.Vector2

sealed class PointerAction {
    object None : PointerAction()
    data class Down(override val position: Vector2) : PointerAction(),
        ActionWithPosition

    data class Move(override val position: Vector2) : PointerAction(),
        ActionWithPosition

    data class Up(override val position: Vector2) : PointerAction(),
        ActionWithPosition

    interface ActionWithPosition {
        val position: Vector2
    }
}

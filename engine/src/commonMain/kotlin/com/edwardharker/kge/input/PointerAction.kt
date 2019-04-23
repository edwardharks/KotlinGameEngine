package com.edwardharker.kge.input

sealed class PointerAction {
    object None : PointerAction()
    data class Down(override val x: Float, override val y: Float) : PointerAction(),
        ActionWithPosition

    data class Move(override val x: Float, override val y: Float) : PointerAction(),
        ActionWithPosition

    data class Up(override val x: Float, override val y: Float) : PointerAction(),
        ActionWithPosition

    interface ActionWithPosition {
        val x: Float
        val y: Float
    }
}

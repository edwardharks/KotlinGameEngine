package com.edwardharker.kge.input

actual class Input {
    actual val primaryPointer: PointerAction
        get() = PointerAction.None
}
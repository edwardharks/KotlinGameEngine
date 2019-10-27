package com.edwardharker.kge.input

actual class Input actual constructor() {
    actual val primaryPointer: PointerAction
        get() = PointerAction.None
}

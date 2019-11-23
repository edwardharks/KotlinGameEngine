package com.edwardharker.kge.input

import com.edwardharker.kge.World
import com.edwardharker.kge.system.UpdateSystem

actual class Input : UpdateSystem {
    actual val primaryPointer: PointerAction
        get() = PointerAction.None

    override fun update(world: World, deltaTime: Float) {}
}

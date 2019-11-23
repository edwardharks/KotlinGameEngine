package com.edwardharker.kge.input

import com.edwardharker.kge.system.UpdateSystem

expect class Input() : UpdateSystem {
    val primaryPointer: PointerAction
}

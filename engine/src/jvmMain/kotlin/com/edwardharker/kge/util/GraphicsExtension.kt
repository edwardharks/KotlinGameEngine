package com.edwardharker.kge.util

import java.awt.Graphics

fun Graphics.create(block: Graphics.() -> Unit) {
    val graphics = this.create()
    graphics.block()
    graphics.dispose()
}

package com.edwardharker.kge.util

import java.awt.Graphics
import java.awt.Graphics2D

fun Graphics.create(block: Graphics2D.() -> Unit) {
    val graphics = this.create() as Graphics2D
    graphics.block()
    graphics.dispose()
}

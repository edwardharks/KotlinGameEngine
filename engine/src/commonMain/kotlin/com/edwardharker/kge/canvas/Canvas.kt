package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.Renderer

expect class Canvas() {
    fun addRenderer(renderer: Renderer)

    internal fun startRender()
    internal fun endRender()
}
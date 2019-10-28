package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.RenderCommand
import com.edwardharker.kge.render.Renderer
import android.graphics.Canvas as AndroidCanvas

actual class Canvas actual constructor() {
    private val renderers = mutableListOf<Renderer>()

    private var currentRenderCommands: List<RenderCommand> = listOf()
    private var nextRenderCommands: MutableList<RenderCommand> = mutableListOf()
    private val lock = Any()

    var invalidate: () -> Unit = {}

    actual fun addRenderer(renderer: Renderer) {
        renderer.addRenderCommand = this::addRenderCommand
        renderers += renderer
    }

    internal actual fun startRender() {
        synchronized(lock) {
            nextRenderCommands = mutableListOf()
        }
    }

    internal actual fun endRender() {
        synchronized(lock) {
            currentRenderCommands = nextRenderCommands
            nextRenderCommands = mutableListOf()
        }
        invalidate()
    }

    internal fun draw(canvas: AndroidCanvas) {
        val renderCommands = synchronized(lock) {
            return@synchronized currentRenderCommands.toList()
        }
        renderCommands.forEach { command ->
            command.invoke(canvas)
        }
    }

    private fun addRenderCommand(command: RenderCommand) {
        synchronized(lock) {
            nextRenderCommands.add(command)
        }
    }
}
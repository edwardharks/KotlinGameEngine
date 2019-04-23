package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.RenderCommand
import com.edwardharker.kge.render.Renderer
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel


actual class Canvas : JPanel() {
    private val renderers = mutableListOf<Renderer>()

    private var currentRenderCommands: List<RenderCommand> = listOf()
    private var nextRenderCommands: MutableList<RenderCommand> = mutableListOf()
    private val lock = Any()

    init {
        background = Color.BLACK
        setSize(400, 400)
    }

    actual fun addRenderer(renderer: Renderer) {
        renderer.addRenderCommand = this::addRenderCommand
        renderers += renderer
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        if (g != null) {
            val renderCommands = synchronized(lock) {
                return@synchronized currentRenderCommands.toList()
            }
            renderCommands.forEach { command ->
                val graphics = g.create() as Graphics2D
                command.invoke(graphics)
                graphics.dispose()
            }
        }
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
        repaint()
    }

    private fun addRenderCommand(command: RenderCommand) {
        synchronized(lock) {
            nextRenderCommands.add(command)
        }
    }
}
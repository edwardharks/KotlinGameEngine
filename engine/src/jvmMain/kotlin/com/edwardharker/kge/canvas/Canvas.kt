package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.RenderCommand
import com.edwardharker.kge.render.Renderer
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


actual class Canvas : JPanel() {
    private val renderers = mutableListOf<Renderer>()

    private var currentRenderCommands: List<RenderCommand> = listOf()
    private var nextRenderCommands: MutableList<RenderCommand> = mutableListOf()
    private val renderCommandLock = Any()

    private var endRenderContinuation: Continuation<Unit>? = null
    private val endRenderContinuationLock = Any()

    actual fun addRenderer(renderer: Renderer) {
        renderer.addRenderCommand = this::addRenderCommand
        renderers += renderer
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        if (g != null) {
            val renderCommands = synchronized(renderCommandLock) {
                return@synchronized currentRenderCommands.toList()
            }
            renderCommands.forEach { command ->
                command.invoke(g)
            }
        }
        synchronized(endRenderContinuationLock) {
            endRenderContinuation?.resume(Unit)
            endRenderContinuation = null
        }
    }

    internal actual fun startRender() {
        synchronized(endRenderContinuationLock) {
            check(endRenderContinuation == null)
        }
        synchronized(renderCommandLock) {
            nextRenderCommands = mutableListOf()
        }
    }

    internal actual suspend fun endRender() {
        synchronized(endRenderContinuationLock) {
            check(endRenderContinuation == null)
        }
        synchronized(renderCommandLock) {
            currentRenderCommands = nextRenderCommands
            nextRenderCommands = mutableListOf()
        }
        return suspendCoroutine { continuation ->
            synchronized(endRenderContinuationLock) {
                endRenderContinuation = continuation
            }
            repaint()
        }
    }

    private fun addRenderCommand(command: RenderCommand) {
        synchronized(renderCommandLock) {
            nextRenderCommands.add(command)
        }
    }
}

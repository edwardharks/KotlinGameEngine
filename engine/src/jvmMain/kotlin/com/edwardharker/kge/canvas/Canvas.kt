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

    private var renderCommands: MutableList<RenderCommand> = mutableListOf()
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
                return@synchronized renderCommands.toList()
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
            renderCommands = mutableListOf()
        }
    }

    internal actual suspend fun endRender() {
        return suspendCoroutine { continuation ->
            synchronized(endRenderContinuationLock) {
                check(endRenderContinuation == null)
                endRenderContinuation = continuation
            }
            repaint()
        }
    }

    private fun addRenderCommand(command: RenderCommand) {
        synchronized(renderCommandLock) {
            renderCommands.add(command)
        }
    }
}

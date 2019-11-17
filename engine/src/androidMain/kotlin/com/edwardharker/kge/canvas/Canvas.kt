package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.RenderCommand
import com.edwardharker.kge.render.Renderer
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import android.graphics.Canvas as AndroidCanvas

actual class Canvas actual constructor() {
    private val renderers = mutableListOf<Renderer>()

    private var currentRenderCommands: List<RenderCommand> = listOf()
    private var nextRenderCommands: MutableList<RenderCommand> = mutableListOf()
    private val renderCommandLock = Any()

    private var endRenderContinuation: Continuation<Unit>? = null
    private val endRenderContinuationLock = Any()

    var invalidate: () -> Unit = {}

    actual fun addRenderer(renderer: Renderer) {
        renderer.addRenderCommand = this::addRenderCommand
        renderers += renderer
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
            invalidate()
        }
    }

    internal fun draw(canvas: AndroidCanvas) {
        val renderCommands = synchronized(renderCommandLock) {
            return@synchronized currentRenderCommands.toList()
        }
        renderCommands.forEach { command ->
            command.invoke(canvas)
        }
        synchronized(endRenderContinuationLock) {
            endRenderContinuation?.resume(Unit)
            endRenderContinuation = null
        }
    }

    private fun addRenderCommand(command: RenderCommand) {
        synchronized(renderCommandLock) {
            nextRenderCommands.add(command)
        }
    }
}
package com.edwardharker.kge.canvas

import com.edwardharker.kge.render.RenderCommand
import com.edwardharker.kge.render.Renderer
import kotlinx.coroutines.isActive
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import android.graphics.Canvas as AndroidCanvas

actual class Canvas actual constructor() {
    private val renderers = mutableListOf<Renderer>()

    private var renderCommands: MutableList<RenderCommand> = mutableListOf()
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
            checkContinuationIsNotActive()
        }
        synchronized(renderCommandLock) {
            renderCommands = mutableListOf()
        }
    }

    internal actual suspend fun endRender() {
        return suspendCoroutine { continuation ->
            synchronized(endRenderContinuationLock) {
                checkContinuationIsNotActive()
                endRenderContinuation = continuation
            }
            invalidate()
        }
    }

    internal fun draw(canvas: AndroidCanvas) {
        val renderCommands = synchronized(renderCommandLock) {
            return@synchronized renderCommands.toList()
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
            renderCommands.add(command)
        }
    }

    private fun checkContinuationIsNotActive() {
        val continuation = endRenderContinuation
        check(continuation == null || !continuation.context.isActive)
    }
}
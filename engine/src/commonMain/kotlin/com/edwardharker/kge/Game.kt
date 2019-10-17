package com.edwardharker.kge

import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.util.getCurrentTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.max

class Game(
    val world: World
) {
    val canvas: Canvas
        get() = world.canvas

    val input: Input
        get() = world.input

    fun start(scope: CoroutineScope = GlobalScope): Job = scope.launch {
        var previous = getCurrentTimeMillis()
        var lag = 0.0
        var lastUpdateTime = getCurrentTimeMillis()

        while (coroutineContext.isActive) {
            val current = getCurrentTimeMillis()
            val elapsed = current - previous
            previous = current
            lag += elapsed

            while (lag >= MS_PER_UPDATE) {
                val currentUpdateTime = getCurrentTimeMillis()

                val beforeInput = getCurrentTimeMillis()
                world.handleInput()
                println("Input duration: ${beforeInput - getCurrentTimeMillis()}")

                val beforeUpdate = getCurrentTimeMillis()
                world.update(currentUpdateTime - lastUpdateTime)
                println("Update duration: ${beforeUpdate - getCurrentTimeMillis()}")

                lag -= MS_PER_UPDATE
                lastUpdateTime = currentUpdateTime
            }

            val beforeRender = getCurrentTimeMillis()
            world.render()
            println("Render duration: ${beforeRender - getCurrentTimeMillis()}")

            delay(max(current + MS_PER_FRAME - getCurrentTimeMillis(), 0))
        }
    }

    companion object {
        private const val MS_PER_UPDATE = 16
        private const val MS_PER_FRAME = 16
    }
}

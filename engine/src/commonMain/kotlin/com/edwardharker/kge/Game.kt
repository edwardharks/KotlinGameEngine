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

        while (isActive) {
            val current = getCurrentTimeMillis()
            val elapsed = current - previous
            previous = current
            lag += elapsed

            while (lag >= MS_PER_UPDATE) {
                world.handleInput()

                world.update(MS_PER_UPDATE)

                lag -= MS_PER_UPDATE
            }

            world.render()
        }
    }

    private companion object {
        private const val MS_PER_UPDATE = 1000f / 60f // 60fps
    }
}

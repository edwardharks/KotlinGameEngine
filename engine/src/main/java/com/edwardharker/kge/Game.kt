package com.edwardharker.kge

import java.util.concurrent.atomic.AtomicBoolean

class Game(
    val world: World
) {
    private val isRunning = AtomicBoolean(true)

    fun start() {
        var previous = getCurrentTime()
        var lag = 0.0
        var lastUpdateTime = getCurrentTime()
        isRunning.getAndSet(true)
        while (isRunning.get()) {
            val current = getCurrentTime()
            val elapsed = current - previous
            previous = current
            lag += elapsed

            while (lag >= MS_PER_UPDATE) {
                val currentUpdateTime = getCurrentTime()
                world.update(currentUpdateTime - lastUpdateTime)
                lag -= MS_PER_UPDATE
                lastUpdateTime = currentUpdateTime
            }

            world.render()

            Thread.sleep(Math.max(current + MS_PER_FRAME - getCurrentTime(), 0))
        }
    }

    fun pause() {
        isRunning.getAndSet(false)
    }

    private fun getCurrentTime(): Long = System.currentTimeMillis()

    companion object {
        private const val MS_PER_UPDATE = 16
        private const val MS_PER_FRAME = 16
    }
}

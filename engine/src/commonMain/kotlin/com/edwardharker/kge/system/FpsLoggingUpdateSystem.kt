package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.RenderDebugInfoComponent

object FpsLoggingUpdateSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        world.forEachEntityWithComponent { _, debugInfo: RenderDebugInfoComponent ->
            println("Render FPS: ${debugInfo.fps}")
        }
    }
}

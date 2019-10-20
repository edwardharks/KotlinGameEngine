package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.RenderDebugInfoComponent

object FpsLoggingUpdateSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        if (deltaTime > 0) {
            println("Update: FPS: ${1000f / deltaTime}")
        }

        world.forEachEntityWithComponent { _, debugInfo: RenderDebugInfoComponent ->
            println("Render FPS: ${debugInfo.fps}")
        }
    }
}

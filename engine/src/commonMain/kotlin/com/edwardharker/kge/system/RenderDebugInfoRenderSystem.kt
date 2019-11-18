package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.RenderDebugInfoComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.util.getCurrentTimeMillis

object RenderDebugInfoRenderSystem : RenderSystem {
    override fun render(world: World) {
        if (!world.containsComponent(RenderDebugInfoComponent::class)) {
            world.addEntityWithComponents(Entity.create(), listOf(RenderDebugInfoComponent()))
        }

        world.forEachEntityWithComponent { _, debugInfo: RenderDebugInfoComponent ->
            val lastRenderTime = debugInfo.lastRenderTime
            val currentRenderTime = getCurrentTimeMillis()
            val deltaTime = currentRenderTime - lastRenderTime
            val fps = if (deltaTime > 0) 1000f / deltaTime else debugInfo.fps

            debugInfo.apply {
                this.lastRenderTime = currentRenderTime
                this.deltaTime = deltaTime
                this.fps = fps
            }
        }
    }
}
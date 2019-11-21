package com.edwardharker.kge.wormgame.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.wormgame.component.WormComponent

object WormMovementSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        world.forEachEntityWithComponents { _,
                                            transform: TransformComponent,
                                            worm: WormComponent ->
            transform.position.y -= worm.speed * deltaTime
        }
    }
}

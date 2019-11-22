package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.ColliderComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt

object CircleSpriteColliderSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        world.forEachEntityWithComponents { _,
                                            transform: TransformComponent,
                                            circle: CircleSpriteComponent,
                                            collider: ColliderComponent ->
            collider.bounds = circle.getBoundsAt(transform.position)
        }
    }
}

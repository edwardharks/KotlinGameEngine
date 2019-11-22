package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.ColliderComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt

object RectangleSpriteColliderSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        world.forEachEntityWithComponents { _,
                                            transform: TransformComponent,
                                            rectangle: RectangleSpriteComponent,
                                            collider: ColliderComponent ->
            collider.bounds = rectangle.getBoundsAt(transform.position)
        }
    }
}

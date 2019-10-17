package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.RectangleColliderComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Vector2

// TODO 1. rename to something like RectangleColliderCheckerSystem
// TODO 2. Make another system that acts on any entity with a RectangleComponent and
//  RectangleColliderComponent to update the RectangleColliderComponent with the position of the
//  RectangleComponent
// TODO 3. make a function that can add both systems when I wants Rectangle collider so I can't
//  forget to add both (maybe it's a good idea to always create the Systems with a factory function
//  so it's consistent and easy to make changes)
object RectangleColliderSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity,
                                            collider: RectangleColliderComponent,
                                            transform: TransformComponent ->
            val width = Vector2.distance(collider.topLeft, collider.topRight)
            val height = Vector2.distance(collider.topLeft, collider.topRight)

            // TODO check if transform is colliding with collider and fire event if it is
        }
    }
}

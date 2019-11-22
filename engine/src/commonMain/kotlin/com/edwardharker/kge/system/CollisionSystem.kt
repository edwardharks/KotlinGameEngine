package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.ColliderComponent
import com.edwardharker.kge.component.Collision
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.util.getIntersectingBounds

object CollisionSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        val entitiesWithCollisionComponents = world.getEntitiesWithComponents(
            ColliderComponent::class,
            CollisionComponent::class
        )
        val entitiesWithColliders = world.getEntitiesWithComponent(ColliderComponent::class)

        for ((entity1, collider1, collision1) in entitiesWithCollisionComponents) {
            val bounds1 = collider1.bounds
            collision1.collisions.clear()
            for ((entity2, collider2) in entitiesWithColliders) {
                if (entity1 != entity2) {
                    val bounds2 = collider2.bounds
                    val intersectingBounds = bounds1.getIntersectingBounds(bounds2)
                    if (intersectingBounds != null) {
                        collision1.collisions.add(Collision(entity2, intersectingBounds))
                    }
                }
            }
        }
    }
}

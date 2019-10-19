package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.Collision
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.util.getIntersectingBounds

object RectangleCollisionSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        val entities = world.getEntitiesWithComponents(
            TransformComponent::class,
            RectangleSpriteComponent::class,
            CollisionComponent::class
        )

        for ((entity1, transform1, rect1, _) in entities) {
            val bounds1 = rect1.getBoundsAt(transform1.position)
            val collisions = mutableListOf<Collision>()
            for ((entity2, transform2, rect2, _) in entities) {
                if (entity1 != entity2) {
                    val bounds2 = rect2.getBoundsAt(transform2.position)
                    val intersectingBounds = bounds1.getIntersectingBounds(bounds2)
                    if (intersectingBounds != null) {
                        collisions.add(Collision(entity2, intersectingBounds))
                    }
                }
            }
            world.addOrReplaceComponent(entity1, CollisionComponent(collisions))
        }
    }
}
package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.CollisionDebugComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.center
import com.edwardharker.kge.util.height
import com.edwardharker.kge.util.width

object CollisionDebugSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        val debugEntities = world.getComponentsOfType(CollisionDebugComponent::class)?.keys
        if (debugEntities != null) {
            world.removeEntities(debugEntities)
        }

        world.forEachEntityWithComponent { _, collisionComponent: CollisionComponent ->
            for (collision in collisionComponent.collisions) {
                world.addEntityWithComponents(
                    entity = Entity.create(),
                    components = listOf(
                        CollisionDebugComponent,
                        TransformComponent(
                            position = collision.bounds.center
                        ),
                        RectangleSpriteComponent(
                            width = collision.bounds.width,
                            height = collision.bounds.height,
                            colour = GREEN.copy(alpha = 100)
                        )
                    )
                )
            }
        }
    }
}

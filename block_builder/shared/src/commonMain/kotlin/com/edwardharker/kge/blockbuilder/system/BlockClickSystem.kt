package com.edwardharker.kge.blockbuilder.system

import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.blockComponents
import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.PointerAction.*
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.center
import com.edwardharker.kge.util.width

object BlockClickSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            pointer: PointerComponent,
                                            transform: TransformComponent,
                                            rect: RectangleSpriteComponent,
                                            collisionComponent: CollisionComponent ->
            if (pointer.primaryPointerAction is Up && collisionComponent.collisions.isNotEmpty()) {
                world.removeComponentOfType(entity, BlockComponent::class)
                world.removeComponent(entity, pointer)

                val collision = collisionComponent.collisions.first()

                world.addOrReplaceComponent(
                    entity = entity,
                    component = rect.copy(width = collision.bounds.width)
                )
                world.addOrReplaceComponent(
                    entity = entity,
                    component = transform.copy(
                        position = transform.position.copy(
                            x = collision.bounds.center.x
                        )
                    )
                )

                world.addEntityWithComponents(
                    entity = Entity.create(),
                    components = blockComponents(
                        x = collision.bounds.center.x,
                        y = rect.getBoundsAt(transform.position).top + rect.height / 2,
                        width = collision.bounds.width
                    )
                )
            }
        }
    }
}
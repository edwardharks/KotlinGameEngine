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
import com.edwardharker.kge.input.PointerAction
import com.edwardharker.kge.system.UpdateSystem

object BlockClickSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            pointer: PointerComponent,
                                            transform: TransformComponent,
                                            rect: RectangleSpriteComponent,
                                            collision: CollisionComponent ->
            if (pointer.primaryPointerAction is PointerAction.Up) {
                world.removeComponentOfType(entity, BlockComponent::class)
                world.removeComponent(entity, pointer)
                world.addEntityWithComponents(
                    entity = Entity.create(),
                    components = blockComponents(
                        y = rect.getBoundsAt(transform.position).top + rect.height / 2
                    )
                )
            }
        }
    }
}
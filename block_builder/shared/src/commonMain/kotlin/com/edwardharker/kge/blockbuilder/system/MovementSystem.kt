package com.edwardharker.kge.blockbuilder.system

import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.blockbuilder.component.BlockDirection
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Vector2

object MovementSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        world.forEachEntityWithComponents { entity: Entity,
                                            transform: TransformComponent,
                                            block: BlockComponent ->
            val speed = when (block.direction) {
                BlockDirection.Left -> -block.speed
                BlockDirection.Right -> block.speed
            }
            val newPosition = Vector2(
                x = transform.position.x + speed * deltaTime,
                y = transform.position.y
            )
            world.addOrReplaceComponent(
                entity = entity,
                component = transform.copy(position = newPosition)
            )
        }
    }
}
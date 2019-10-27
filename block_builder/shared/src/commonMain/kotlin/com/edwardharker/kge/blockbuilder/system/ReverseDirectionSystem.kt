package com.edwardharker.kge.blockbuilder.system

import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.blockbuilder.component.BlockDirection.*
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.UpdateSystem

class ReverseDirectionSystem(
    private val left: Float,
    private val right: Float
) : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            transform: TransformComponent,
                                            rect: RectangleSpriteComponent,
                                            block: BlockComponent ->
            val bounds = rect.getBoundsAt(transform.position)
            if (bounds.left <= left) {
                world.addOrReplaceComponent(
                    entity = entity,
                    component = block.copy(direction = Right)
                )
            } else if (bounds.right >= right) {
                world.addOrReplaceComponent(
                    entity = entity,
                    component = block.copy(direction = Left)
                )
            }
        }
    }
}
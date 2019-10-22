package com.edwardharker.kge.blockbuilder.system

import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.blockComponents
import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.blockbuilder.gameHeight
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.PointerAction
import com.edwardharker.kge.input.PointerAction.*
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Colour.Companion.WHITE
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.util.center
import com.edwardharker.kge.util.width

object AddBlockFailSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            pointer: PointerComponent,
                                            collisionComponent: CollisionComponent ->
            if (pointer.primaryPointerAction is Down && collisionComponent.collisions.isEmpty()) {
                world.removeComponentOfType(entity, BlockComponent::class)
                world.removeComponent(entity, pointer)

                world.addEntityWithComponents(
                    entity = Entity.create(),
                    components = listOf(
                        TransformComponent(
                            position = Vector2(
                                x = 0f,
                                y = gameHeight / 2
                            )
                        ),
                        TextComponent(
                            text = "Fail",
                            size = 40f,
                            colour = WHITE
                        )
                    )
                )
            }
        }
    }
}
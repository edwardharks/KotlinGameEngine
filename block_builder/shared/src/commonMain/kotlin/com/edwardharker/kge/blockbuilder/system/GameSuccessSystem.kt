package com.edwardharker.kge.blockbuilder.system

import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.blockbuilder.gameHeight
import com.edwardharker.kge.blockbuilder.gameWidth
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.getBoundsAt
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Colour.Companion.WHITE
import com.edwardharker.kge.util.Vector2

object GameSuccessSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity,
                                            transform: TransformComponent,
                                            rect: RectangleSpriteComponent,
                                            _: BlockComponent ->
            val bounds = rect.getBoundsAt(transform.position)
            if (bounds.top > gameHeight) {
                world.removeComponentOfType(entity, BlockComponent::class)
                world.removeComponentOfType(entity, PointerComponent::class)

                world.addEntityWithComponents(
                    entity = Entity.create(),
                    components = listOf(
                        TransformComponent(
                            position = Vector2(
                                x = 0f,
                                y = gameHeight / 2
                            )
                        ),
                        RectangleSpriteComponent(
                            height = 48f,
                            width = gameWidth,
                            colour = WHITE
                        ),
                        TextComponent(
                            text = "Success",
                            size = 40f,
                            colour = GREEN
                        )
                    )
                )
            }
        }
    }
}
package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.input.PointerAction.*
import com.edwardharker.kge.util.Colour.Companion.BLUE
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Colour.Companion.RED

/**
 * Add to a world to visualise inputs in the game
 */
object InputDebugSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Float) {
        val inputDebugComponents = world.getComponentsOfType(InputDebugComponent::class)
        if (inputDebugComponents.isNullOrEmpty()) {
            world.addEntityWithComponents(
                components = listOf(
                    InputDebugComponent,
                    PointerComponent(None)
                )
            )
        }

        val debugVisualEntities = world.getComponentsOfType(InputDebugVisualComponent::class)?.keys
        if (debugVisualEntities != null) {
            world.removeEntities(debugVisualEntities)
        }

        world.forEachEntityWithComponents { _,
                                            _: InputDebugComponent,
                                            pointer: PointerComponent ->
            val action = pointer.primaryPointerAction
            if (action is ActionWithPosition) {
                val position = action.position
                val colour = when (action) {
                    is Down -> GREEN
                    is Move -> BLUE
                    is Up -> RED
                    else -> {
                        throw IllegalStateException("unknown action which is ActionWithPosition")
                    }
                }

                println("primaryPointerAction: ${pointer.primaryPointerAction}")

                world.addEntityWithComponents(
                    components = listOf(
                        InputDebugVisualComponent,
                        TransformComponent(
                            position = position
                        ),
                        CircleSpriteComponent(
                            radius = 10f,
                            colour = colour
                        )
                    )
                )
            }
        }
    }
}

private object InputDebugComponent : Component
private object InputDebugVisualComponent : Component
package com.edwardharker.kge.rotatingsquares

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.util.plus
import kotlinx.coroutines.GlobalScope

fun createRotatingSquaresGame(): Game {
    val world = World(
        updateSystems = listOf(RotateSystem),
        renderSystems = listOf(LoggingRenderSystem)
    )

    world.addEntityWithComponents(
        entity = Entity(id = 1),
        components = listOf(
            TransformComponent(),
            RotatePropertiesComponent(
                speed = 0.001f
            )
        )
    )

    world.addEntityWithComponents(
        entity = Entity(id = 2),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 2f,
                    y = 0f
                )
            ),
            RotatePropertiesComponent(
                speed = 0.002f
            )
        )
    )

    world.addEntityWithComponents(
        entity = Entity(id = 3),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 0f,
                    y = 2f
                )
            ),
            RotatePropertiesComponent(
                speed = 0.003f
            )
        )
    )

    world.addEntityWithComponents(
        entity = Entity(id = 4),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 2f,
                    y = 2f
                )
            ),
            RotatePropertiesComponent(
                speed = 0.004f
            )
        )
    )

    return Game(world = world)
}

private data class RotatePropertiesComponent(
    val speed: Float
) : Component

private object RotateSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { transform: TransformComponent,
                                            rotateProperties: RotatePropertiesComponent ->
            transform.rotation = transform.rotation + rotateProperties.speed * deltaTime
        }
    }
}

private object LoggingRenderSystem : RenderSystem {
    override fun render(world: World) {
        println("~~~~~~~~~")
        println()
        world.forEachEntityWithComponents { transform: TransformComponent ->
            println(transform)
        }
        println()
        println("~~~~~~~~~")
        println()
        println()
        println()
        println()
    }
}
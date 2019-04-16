@file:JvmName("Main")

package com.edwardharker.kge.rotatingsquares

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.entity.forEachWithComponents
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.plus

fun main() {
    val game = Game(
        world = World(
            entities = listOf(
                Entity(
                    id = "square-1",
                    components = listOf(
                        TransformComponent(),
                        RotatePropertiesComponent(
                            speed = 0.001f
                        )
                    )
                )
            ),
            updateSystems = listOf(RotateSystem),
            renderSystems = listOf(LoggingRenderSystem)
        )
    )

    game.start()
}

private data class RotatePropertiesComponent(
    val speed: Float
) : Component

private object RotateSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.entities
            .forEachWithComponents { transform: TransformComponent,
                                     rotateProperties: RotatePropertiesComponent ->
                transform.rotation = transform.rotation + rotateProperties.speed * deltaTime
            }
    }
}

private object LoggingRenderSystem : RenderSystem {
    override fun render(world: World) {
        println("~~~~~~~~~")
        println()
        world.entities.forEachWithComponents { transform: TransformComponent ->
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
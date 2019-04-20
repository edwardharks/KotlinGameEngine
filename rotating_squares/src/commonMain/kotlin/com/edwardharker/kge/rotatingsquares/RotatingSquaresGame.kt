package com.edwardharker.kge.rotatingsquares

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.render.RectangleRenderer
import com.edwardharker.kge.system.RectangleRenderSystem
import com.edwardharker.kge.system.UpdateSystem
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.util.plus

fun createRotatingSquaresGame(): Game {
    val canvas = Canvas()

    val rectangleRenderer = RectangleRenderer()
    canvas.addRenderer(rectangleRenderer)

    val world = World(
        updateSystems = listOf(RotateSystem),
        renderSystems = listOf(
            RectangleRenderSystem(rectangleRenderer)
        ),
        canvas = canvas
    )

    val square = RectangleSpriteComponent(
        width = 10f,
        height = 10f,
        colour = Colour.WHITE
    )

    world.addEntityWithComponents(
        entity = Entity(id = 1),
        components = listOf(
            TransformComponent(
                position = Vector2.ZERO
            ),
            square,
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
                    x = 50f,
                    y = 0f
                )
            ),
            square,
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
                    y = 50f
                )
            ),
            square,
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
                    x = 50f,
                    y = 50f
                )
            ),
            square,
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

package com.edwardharker.kge.rotatingsquares

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.input.PointerAction
import com.edwardharker.kge.render.RectangleRenderer
import com.edwardharker.kge.system.PointerSystem
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
        inputSystems = listOf(
            PointerSystem
        ),
        updateSystems = listOf(
            RotateSystem,
            MouseLoggingSystem,
            ChangeDirectionOnClickSystem
        ),
        renderSystems = listOf(
            RectangleRenderSystem(rectangleRenderer)
        ),
        canvas = canvas,
        input = Input()
    )


    world.addEntityWithComponents(
        entity = Entity(id = 1),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 80f,
                    y = 80f
                )
            ),
            RectangleSpriteComponent(
                width = 50f,
                height = 50f,
                colour = Colour.WHITE
            ),
            RotatePropertiesComponent(
                speed = 0.001f
            ),
            PointerComponent()
        )
    )

    world.addEntityWithComponents(
        entity = Entity(id = 2),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 180f,
                    y = 80f
                )
            ),
            RectangleSpriteComponent(
                width = 50f,
                height = 50f,
                colour = Colour.RED
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
                    x = 80f,
                    y = 180f
                )
            ),
            RectangleSpriteComponent(
                width = 50f,
                height = 50f,
                colour = Colour.GREEN
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
                    x = 180f,
                    y = 180f
                )
            ),
            RectangleSpriteComponent(
                width = 50f,
                height = 50f,
                colour = Colour.BLUE
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

private object ChangeDirectionOnClickSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            pointer: PointerComponent,
                                            rotateProperties: RotatePropertiesComponent ->
            if (pointer.primaryPointerAction is PointerAction.Up) {
                world.addOrReplaceComponent(
                    entity = entity,
                    component = rotateProperties.copy(speed = rotateProperties.speed * -1)
                )
            }
        }
    }
}

private object RotateSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponents { entity: Entity,
                                            transform: TransformComponent,
                                            rotateProperties: RotatePropertiesComponent ->
            world.addOrReplaceComponent(
                entity,
                transform.copy(
                    rotation = transform.rotation + rotateProperties.speed * deltaTime
                )
            )
        }
    }
}

private object MouseLoggingSystem : UpdateSystem {
    override fun update(world: World, deltaTime: Long) {
        world.forEachEntityWithComponent { _, pointerComponent: PointerComponent ->
            println(pointerComponent)
        }
    }
}

package com.edwardharker.kge.blockbuilder

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.render.RectangleRenderer
import com.edwardharker.kge.system.PointerSystem
import com.edwardharker.kge.system.RectangleRenderSystem
import com.edwardharker.kge.system.cameraSystem
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

const val blockHeight = 30f
const val initialBlockWidth = 160f


fun createBlockBuilderGame(): Game {
    val canvas = Canvas()

    val rectangleRenderer = RectangleRenderer()
    canvas.addRenderer(rectangleRenderer)

    val world = World(
        inputSystems = listOf(
            PointerSystem
        ),
        updateSystems = cameraSystem(),
        renderSystems = listOf(
            RectangleRenderSystem(rectangleRenderer)
        ),
        canvas = canvas,
        input = Input()
    )

    // Camera
    world.addEntityWithComponents(
        entity = Entity(0),
        components = cameraComponent(
            position = Vector2(
                x = -200f,
                y = 400f
            )
        )
    )

    // Background
    world.addEntityWithComponents(
        entity = Entity.create(),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 0f,
                    y = 200f
                )
            ),
            RectangleSpriteComponent(
                width = 400f,
                height = 400f,
                colour = Colour.GREY
            )
        )
    )

    // Initial block
    world.addEntityWithComponents(
        entity = Entity.create(),
        components = listOf(
            TransformComponent(
                position = Vector2(
                    x = 0f,
                    y = blockHeight / 2
                )
            ),
            RectangleSpriteComponent(
                width = initialBlockWidth,
                height = blockHeight,
                colour = Colour.BLUE
            )
        )
    )

    return Game(world = world)
}

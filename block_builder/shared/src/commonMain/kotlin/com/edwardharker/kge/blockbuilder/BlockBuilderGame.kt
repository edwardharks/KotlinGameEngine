package com.edwardharker.kge.blockbuilder

import com.edwardharker.kge.Game
import com.edwardharker.kge.World
import com.edwardharker.kge.blockbuilder.system.BlockClickSystem
import com.edwardharker.kge.blockbuilder.system.MovementSystem
import com.edwardharker.kge.blockbuilder.system.ReverseDirectionSystem
import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.render.CameraRenderer
import com.edwardharker.kge.render.RectangleRenderer
import com.edwardharker.kge.system.CameraRenderSystem
import com.edwardharker.kge.system.PointerSystem
import com.edwardharker.kge.system.RectangleCollisionSystem
import com.edwardharker.kge.system.RectangleRenderSystem
import com.edwardharker.kge.system.cameraSystem
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

const val blockHeight = 40f
const val initialBlockWidth = 160f
const val gameWidth = 400f
const val gameHeight = 400f


fun createBlockBuilderGame(): Game {
    val canvas = Canvas()

    val rectangleRenderer = RectangleRenderer()
    canvas.addRenderer(rectangleRenderer)

    val cameraRenderer = CameraRenderer()
    canvas.addRenderer(cameraRenderer)

    val world = World(
        inputSystems = listOf(
            PointerSystem
        ),
        updateSystems = cameraSystem() + listOf(
            MovementSystem,
            ReverseDirectionSystem(
                left = -(gameWidth / 2),
                right = gameWidth / 2
            ),
            RectangleCollisionSystem,
            BlockClickSystem
        ),
        renderSystems = listOf(
            CameraRenderSystem(cameraRenderer),
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
            ),
            size = gameHeight / 2,
            aspectRatio = gameWidth / gameHeight
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

    // Initial blocks
    world.addEntityWithComponents(
        entity = Entity.create(),
        components = staticBlockComponents()
    )
    world.addEntityWithComponents(
        entity = Entity.create(),
        components = blockComponents()
    )

    return Game(world = world)
}

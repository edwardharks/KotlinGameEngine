package com.edwardharker.kge.blockbuilder

import com.edwardharker.kge.Game
import com.edwardharker.kge.blockbuilder.system.AddBlockFailSystem
import com.edwardharker.kge.blockbuilder.system.AddBlockSystem
import com.edwardharker.kge.blockbuilder.system.GameSuccessSystem
import com.edwardharker.kge.blockbuilder.system.MovementSystem
import com.edwardharker.kge.blockbuilder.system.ReverseDirectionSystem
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.input.PointerAction
import com.edwardharker.kge.system.FpsLoggingUpdateSystem
import com.edwardharker.kge.util.Colour.Companion.BLUE
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Colour.Companion.GREY
import com.edwardharker.kge.util.Colour.Companion.RED
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.world

const val blockHeight = 40f
const val initialBlockWidth = 160f
const val gameWidth = 400f
const val gameHeight = 400f
val blockColours = listOf(BLUE, GREEN, RED)

fun createBlockBuilderGame(): Game {
    val world = world {
        updateSystems {
            +MovementSystem
            +ReverseDirectionSystem
            +AddBlockFailSystem
            +AddBlockSystem
            +GameSuccessSystem
            +FpsLoggingUpdateSystem
        }

        gameObjects {
            // Camera
            +cameraComponent(
                position = Vector2(
                    x = 0f,
                    y = gameHeight / 2
                ),
                size = gameHeight / 2,
                aspectRatio = gameWidth / gameHeight
            )

            // Background
            entityWithComponents {
                +TransformComponent(
                    position = Vector2(
                        x = 0f,
                        y = gameHeight / 2
                    )
                )
                +RectangleSpriteComponent(
                    width = gameWidth,
                    height = gameHeight,
                    colour = GREY
                )
            }

            // Blocks
            +staticBlockComponent()
            +blockComponent(PointerAction.None)
        }
    }

    return Game(world = world)
}

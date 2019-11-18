package com.edwardharker.kge.wormgame

import com.edwardharker.kge.Game
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.system.FpsLoggingUpdateSystem
import com.edwardharker.kge.util.Colour.Companion.GREY
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.world

const val gameWidth = 450f
const val gameHeight = 800f

fun createWormGame(): Game {
    val world = world {
        updateSystems {
            +FpsLoggingUpdateSystem
        }

        gameObjects {
            // Camera
            +cameraComponent(
                position = Vector2(
                    x = -gameWidth / 2,
                    y = gameHeight
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
        }
    }

    return Game(world = world)
}

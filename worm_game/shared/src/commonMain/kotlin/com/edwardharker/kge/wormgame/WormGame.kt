package com.edwardharker.kge.wormgame

import com.edwardharker.kge.Game
import com.edwardharker.kge.system.CollisionDebugSystem
import com.edwardharker.kge.util.Colour.Companion.BLACK
import com.edwardharker.kge.util.Colour.Companion.BLUE
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Colour.Companion.RED
import com.edwardharker.kge.world
import com.edwardharker.kge.wormgame.gameobject.background
import com.edwardharker.kge.wormgame.gameobject.bird
import com.edwardharker.kge.wormgame.gameobject.camera
import com.edwardharker.kge.wormgame.gameobject.worm
import com.edwardharker.kge.wormgame.system.WormMovementSystem

const val gameWidth = 450f
const val gameHeight = 800f
const val columns = 4
const val columnSize = 80f

fun createWormGame(): Game {
    val world = world {
        updateSystems {
            +WormMovementSystem
            +CollisionDebugSystem
        }

        gameObjects {
            +camera()

            +background()

            +bird(
                column = 0,
                colour = GREEN
            )
            +bird(
                column = 1,
                colour = BLUE
            )
            +bird(
                column = 2,
                colour = RED
            )
            +bird(
                column = 3,
                colour = BLACK
            )

            +worm(
                column = 0,
                colour = GREEN,
                units = 2
            )
            +worm(
                column = 1,
                colour = BLUE,
                units = 3
            )
            +worm(
                column = 2,
                colour = RED,
                units = 1
            )
            +worm(
                column = 3,
                colour = BLACK,
                units = 4
            )
        }
    }

    return Game(world = world)
}

fun calculateColumnX(column: Int): Float {
    val halfWidth = columnSize / 2
    return columnSpacing * (column + 1) + halfWidth + columnSize * column
}

private val columnSpacing: Float
    get() = (gameWidth - columnSize * columns) / (columns + 1)

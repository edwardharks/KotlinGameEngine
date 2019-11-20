package com.edwardharker.kge.wormgame

import com.edwardharker.kge.Game
import com.edwardharker.kge.util.Colour.Companion.BLACK
import com.edwardharker.kge.util.Colour.Companion.BLUE
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Colour.Companion.RED
import com.edwardharker.kge.world

const val gameWidth = 450f
const val gameHeight = 800f
const val columns = 4
const val columnSize = 80f

fun createWormGame(): Game {
    val world = world {
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
        }
    }

    return Game(world = world)
}

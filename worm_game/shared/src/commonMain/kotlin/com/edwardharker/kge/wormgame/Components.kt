package com.edwardharker.kge.wormgame

import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Colour.Companion.GREEN
import com.edwardharker.kge.util.Vector2

fun camera(): List<Component> {
    return cameraComponent(
        position = Vector2(
            x = gameWidth / 2,
            y = gameHeight / 2
        ),
        size = gameHeight / 2,
        aspectRatio = gameWidth / gameHeight
    )
}

fun background(): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = gameWidth / 2,
                y = gameHeight / 2
            )
        ),
        RectangleSpriteComponent(
            width = gameWidth,
            height = gameHeight,
            colour = Colour.GREY
        )
    )
}

fun worm(): List<Component> {
    return emptyList()
}

private const val birdMargin = 80f

fun bird(
    column: Int,
    colour: Colour = GREEN
): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = calculateColumnX(column),
                y = birdMargin
            )
        ),
        RectangleSpriteComponent(
            width = columnSize,
            height = columnSize,
            colour = colour
        )
    )
}

private fun calculateColumnX(column: Int): Float {
    val halfWidth = columnSize / 2
    return columnSpacing * (column + 1) + halfWidth + columnSize * column
}

private val columnSpacing: Float
    get() = (gameWidth - columnSize * columns) / (columns + 1)
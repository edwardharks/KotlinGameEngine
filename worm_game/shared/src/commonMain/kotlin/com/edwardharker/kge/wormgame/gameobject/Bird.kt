package com.edwardharker.kge.wormgame.gameobject

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.wormgame.columnSize
import com.edwardharker.kge.wormgame.columns
import com.edwardharker.kge.wormgame.gameWidth

private const val birdBottomMargin = 80f

fun bird(
    column: Int,
    colour: Colour = Colour.GREEN
): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = calculateColumnX(column),
                y = birdBottomMargin
            )
        ),
        CircleSpriteComponent(
            radius = columnSize,
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

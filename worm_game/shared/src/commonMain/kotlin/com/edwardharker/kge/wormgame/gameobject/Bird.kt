package com.edwardharker.kge.wormgame.gameobject

import com.edwardharker.kge.component.CircleSpriteComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.wormgame.calculateColumnX
import com.edwardharker.kge.wormgame.columnSize

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
            radius = columnSize / 2,
            colour = colour
        )
    )
}

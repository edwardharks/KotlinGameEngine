package com.edwardharker.kge.wormgame.gameobject

import com.edwardharker.kge.component.ColliderComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.wormgame.calculateColumnX
import com.edwardharker.kge.wormgame.columnSize
import com.edwardharker.kge.wormgame.component.WormComponent
import com.edwardharker.kge.wormgame.gameHeight

fun worm(
    column: Int,
    units: Int = 1,
    colour: Colour = Colour.GREEN
): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = calculateColumnX(column),
                y = gameHeight * 2
            )
        ),
        RectangleSpriteComponent(
            width = columnSize,
            height = columnSize * units,
            colour = colour,
            cornerRadius = columnSize
        ),
        WormComponent(
            speed = 0.5f
        ),
        ColliderComponent()
    )
}

package com.edwardharker.kge.blockbuilder

import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

fun staticBlockComponents(): List<Component> {
    return listOf(
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
        ),
        CollisionComponent()
    )
}

fun blockComponents(
    colour: Colour = Colour.BLUE,
    x: Float = -gameWidth,
    y: Float = blockHeight + blockHeight / 2
): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = x,
                y = y
            )
        ),
        RectangleSpriteComponent(
            width = initialBlockWidth,
            height = blockHeight,
            colour = colour
        ),
        PointerComponent(),
        CollisionComponent(),
        BlockComponent(
            speed = 0.5f
        )
    )
}
package com.edwardharker.kge.blockbuilder

import com.edwardharker.kge.blockbuilder.component.BlockComponent
import com.edwardharker.kge.component.ColliderComponent
import com.edwardharker.kge.component.CollisionComponent
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.input.PointerAction
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2

fun staticBlockComponent(): List<Component> {
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
            colour = blockColours.random()
        ),
        ColliderComponent(),
        CollisionComponent()
    )
}

fun blockComponent(
    pointerAction: PointerAction,
    x: Float = -gameWidth,
    y: Float = blockHeight + blockHeight / 2,
    width: Float = initialBlockWidth,
    colour: Colour = blockColours.random()
): List<Component> {
    return listOf(
        TransformComponent(
            position = Vector2(
                x = x,
                y = y
            )
        ),
        RectangleSpriteComponent(
            width = width,
            height = blockHeight,
            colour = colour
        ),
        PointerComponent(pointerAction),
        ColliderComponent(),
        CollisionComponent(),
        BlockComponent(
            speed = 0.5f
        )
    )
}
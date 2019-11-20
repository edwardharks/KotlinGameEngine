package com.edwardharker.kge.wormgame.gameobject

import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.RectangleSpriteComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.util.Colour
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.wormgame.gameHeight
import com.edwardharker.kge.wormgame.gameWidth

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

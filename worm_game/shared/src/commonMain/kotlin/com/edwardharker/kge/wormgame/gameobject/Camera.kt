package com.edwardharker.kge.wormgame.gameobject

import com.edwardharker.kge.component.Component
import com.edwardharker.kge.component.cameraComponent
import com.edwardharker.kge.util.Vector2
import com.edwardharker.kge.wormgame.gameHeight
import com.edwardharker.kge.wormgame.gameWidth

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
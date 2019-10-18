package com.edwardharker.kge.blockbuilder.component

import com.edwardharker.kge.blockbuilder.component.BlockDirection.Right
import com.edwardharker.kge.component.Component

data class BlockComponent(
    val speed: Float = 1f,
    val direction: BlockDirection = Right
) : Component

enum class BlockDirection {
    Left,
    Right,
}

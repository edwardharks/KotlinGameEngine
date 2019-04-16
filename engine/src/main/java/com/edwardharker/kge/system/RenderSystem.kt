package com.edwardharker.kge.system

import com.edwardharker.kge.World

interface RenderSystem {
    fun render(world: World)
}
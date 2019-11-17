package com.edwardharker.kge.system

import com.edwardharker.kge.World

interface UpdateSystem {
    fun update(world: World, deltaTime: Float)
}
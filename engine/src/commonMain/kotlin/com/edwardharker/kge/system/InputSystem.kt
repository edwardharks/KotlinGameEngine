package com.edwardharker.kge.system

import com.edwardharker.kge.World

interface InputSystem {
    fun handleInput(world: World)
}
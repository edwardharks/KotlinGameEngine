package com.edwardharker.kge.component

data class RenderDebugInfoComponent(
    var lastRenderTime: Long = 0,
    var deltaTime: Long = 0,
    var fps: Float = 0f
) : Component

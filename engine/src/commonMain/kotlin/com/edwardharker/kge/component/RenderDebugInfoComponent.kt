package com.edwardharker.kge.component

data class RenderDebugInfoComponent(
    val lastRenderTime: Long = 0,
    val deltaTime: Long = 0,
    val fps: Float = 0f
) : Component

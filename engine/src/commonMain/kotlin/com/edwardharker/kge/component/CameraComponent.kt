package com.edwardharker.kge.component

/**
 * An orthographic camera
 */
data class CameraComponent(
    var aspectRatio: Float = 1f,
    /**
     * Half the height (Copying Unity because I don't know any better)
     */
    var size: Float = 200f
) : Component

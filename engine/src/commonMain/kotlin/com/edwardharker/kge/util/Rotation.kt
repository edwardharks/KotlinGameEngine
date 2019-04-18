package com.edwardharker.kge.util

inline class Rotation(val radians: Float) {
    companion object {
        val ZERO: Rotation = Rotation(0.0f)
    }
}

operator fun Rotation.plus(value: Float) = Rotation(radians + value)
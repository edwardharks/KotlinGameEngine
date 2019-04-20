package com.edwardharker.kge.util

/**
 * Values are 0 - 255
 */
data class Colour(
    val alpha: Int,
    val red: Int,
    val green: Int,
    val blue: Int
) {
    companion object {
        val BLACK = Colour(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        )
        val WHITE = Colour(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        )

        val RED = Colour(
            alpha = 255,
            red = 255,
            green = 0,
            blue = 0
        )

        val GREEN = Colour(
            alpha = 255,
            red = 0,
            green = 255,
            blue = 0
        )

        val BLUE = Colour(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 255
        )
    }
}

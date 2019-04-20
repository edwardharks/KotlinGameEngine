package com.edwardharker.kge.util

import java.awt.Color

fun Colour.toColor(): Color {
    return Color(red, green, blue, alpha)
}
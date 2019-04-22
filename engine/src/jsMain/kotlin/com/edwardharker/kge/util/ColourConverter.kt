package com.edwardharker.kge.util


fun Colour.toJsColor(): String {
    return "rgba($red, $green, $blue, ${1 - (255 - alpha) / 255.0})"
}
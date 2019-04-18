package com.edwardharker.kge.rotatingsquares

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    createRotatingSquaresGame().start().join()
}
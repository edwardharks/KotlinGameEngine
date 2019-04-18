package com.edwardharker.kge.rotatingsquares

fun main() {
    val gameJob = createRotatingSquaresGame().start()
    while (!gameJob.isCompleted) {
    }
}
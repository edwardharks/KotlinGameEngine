package com.edwardharker.kge.rotatingsquares

import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame

fun main() = runBlocking {
    val game = createRotatingSquaresGame()

    game.canvas.addMouseListener(game.input)
    game.canvas.addMouseMotionListener(game.input)

    val frame = JFrame("Rotating Squares")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(game.canvas)
    frame.size = Dimension(400, 400)
    frame.isResizable = true
    frame.background = Color.BLACK
    frame.setLocationRelativeTo(null)
    frame.isVisible = true

    game.start().join()
}
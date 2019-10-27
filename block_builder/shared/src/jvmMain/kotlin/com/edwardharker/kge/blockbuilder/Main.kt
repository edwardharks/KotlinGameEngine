package com.edwardharker.kge.blockbuilder

import kotlinx.coroutines.runBlocking
import java.awt.Dimension
import javax.swing.JFrame

fun main() = runBlocking {
    val game = createBlockBuilderGame()

    game.canvas.addMouseListener(game.input)
    game.canvas.addMouseMotionListener(game.input)

    val frame = JFrame("Block Builder")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(game.canvas)
    frame.size = Dimension(500, 500)
    frame.isResizable = true
    frame.setLocationRelativeTo(null)
    frame.isVisible = true

    game.start().join()
}

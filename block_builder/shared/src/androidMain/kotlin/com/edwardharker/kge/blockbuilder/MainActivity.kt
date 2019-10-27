package com.edwardharker.kge.blockbuilder

import android.app.Activity
import android.os.Bundle
import com.edwardharker.kge.GameView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = createBlockBuilderGame()

        val gameView = GameView(this)
        gameView.game = game

        setContentView(gameView)

        game.start()
    }
}
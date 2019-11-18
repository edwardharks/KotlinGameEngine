package com.edwardharker.kge.wormgame

import android.app.Activity
import android.os.Bundle
import com.edwardharker.kge.GameView
import kotlinx.coroutines.Job

class MainActivity : Activity() {
    private val game = createWormGame()
    private var gameJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameView.create(this, game))
    }

    override fun onStart() {
        super.onStart()
        check(gameJob == null)
        gameJob = game.start()
    }

    override fun onStop() {
        super.onStop()
        gameJob?.cancel()
        gameJob = null
    }
}

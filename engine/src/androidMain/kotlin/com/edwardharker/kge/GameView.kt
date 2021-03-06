package com.edwardharker.kge

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class GameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var game: Game? = null
        set(value) {
            field = value
            setOnTouchListener(game?.input)
            game?.canvas?.invalidate = this::postInvalidate
        }

    override fun onDraw(canvas: Canvas) {
        game?.canvas?.draw(canvas)
    }

    companion object {
        fun create(context: Context, game: Game): GameView {
            return GameView(context).apply {
                this.game = game
            }
        }
    }
}

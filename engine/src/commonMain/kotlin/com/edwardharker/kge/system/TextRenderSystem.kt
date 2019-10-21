package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.TextComponent
import com.edwardharker.kge.component.TransformComponent
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.render.TextRenderer

class TextRenderSystem(
    private val textRenderer: TextRenderer
) : RenderSystem {
    override fun render(world: World) {
        world.forEachEntityWithComponents { _: Entity,
                                            transform: TransformComponent,
                                            text: TextComponent ->
            textRenderer.renderText(transform, text)
        }
    }
}

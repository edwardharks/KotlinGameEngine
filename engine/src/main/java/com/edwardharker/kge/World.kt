package com.edwardharker.kge

import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.UpdateSystem

class World(
    entities: List<Entity> = emptyList(),
    private val updateSystems: List<UpdateSystem> = emptyList(),
    private val renderSystems: List<RenderSystem> = emptyList()
) {
    private val _entities = entities.toMutableList()
    val entities: List<Entity>
        get() = _entities

    internal fun update(deltaTime: Long) {
        updateSystems
            .forEach { system ->
                system.update(this, deltaTime)
            }
    }

    internal fun render() {
        renderSystems
            .forEach { system ->
                system.render(this)
            }
    }
}
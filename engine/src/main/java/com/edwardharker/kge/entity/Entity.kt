package com.edwardharker.kge.entity

import com.edwardharker.kge.component.Component

class Entity(val id: String, val components: List<Component>)

inline fun <reified T1 : Component> Iterable<Entity>.forEachWithComponents(action: (T1) -> Unit) {
    for (entity in this) {
        val component1 = entity.components.firstOrNull { it is T1 } as? T1
        if (component1 != null) {
            action(component1)
        }
    }
}

inline fun <reified T1 : Component, reified T2 : Component> Iterable<Entity>.forEachWithComponents(
    action: (T1, T2) -> Unit
) {
    for (entity in this) {
        val component1 = entity.components.firstOrNull { it is T1 } as? T1
        val component2 = entity.components.firstOrNull { it is T2 } as? T2
        if (component1 != null && component2 != null) {
            action(component1, component2)
        }
    }
}
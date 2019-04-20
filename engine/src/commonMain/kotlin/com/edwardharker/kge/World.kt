package com.edwardharker.kge

import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.UpdateSystem
import kotlin.reflect.KClass

private typealias ComponentMap = MutableMap<KClass<out Component>, EntityComponentMap>
private typealias EntityComponentMap = MutableMap<Entity, Component>

class World(
    private val updateSystems: List<UpdateSystem> = emptyList(),
    private val renderSystems: List<RenderSystem> = emptyList(),
    val canvas: Canvas
) {
    private val _entities = mutableSetOf<Entity>()
    val entities: Set<Entity>
        get() = _entities.toSet()

    private val components: ComponentMap = mutableMapOf()

    fun addEntity(entity: Entity) {
        if (_entities.contains(entity)) {
            throw IllegalArgumentException("$entity already exists")
        }
        _entities += entity
    }

    fun addEntityWithComponents(entity: Entity, components: List<Component>) {
        addEntity(entity)
        components.forEach { addComponent(entity, it) }
    }

    fun addComponent(entity: Entity, component: Component) {
        if (!_entities.contains(entity)) {
            throw IllegalStateException("$entity does not exist")
        }

        if (!components.containsKey(component::class)) {
            components[component::class] = mutableMapOf()
        }

        components[component::class]!![entity] = component
    }

    fun <T : Component> getComponentsOfType(componentType: KClass<T>): Map<Entity, T>? {
        @Suppress("UNCHECKED_CAST")
        return components[componentType] as Map<Entity, T>
    }

    inline fun <reified T1 : Component> forEachEntityWithComponent(action: (T1) -> Unit) {
        val components = getComponentsOfType(T1::class)
        components?.values?.forEach { action(it) }
    }

    inline fun <reified T1 : Component, reified T2 : Component> forEachEntityWithComponents(
        action: (T1, T2) -> Unit
    ) {
        val componentMap1 = getComponentsOfType(T1::class)
        val componentMap2 = getComponentsOfType(T2::class)

        for (entity in entities) {
            val component1 = componentMap1?.get(entity)
            val component2 = componentMap2?.get(entity)
            if (component1 != null && component2 != null) {
                action(component1, component2)
            }
        }
    }

    internal fun update(deltaTime: Long) {
        updateSystems
            .forEach { system ->
                system.update(this, deltaTime)
            }
    }

    internal fun render() {
        canvas.startRender()
        renderSystems
            .forEach { system ->
                system.render(this)
            }
        canvas.endRender()
    }
}
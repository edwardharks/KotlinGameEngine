package com.edwardharker.kge

import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.system.InputSystem
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.UpdateSystem
import kotlin.reflect.KClass

private typealias ComponentMap = MutableMap<KClass<out Component>, EntityComponentMap>
private typealias EntityComponentMap = MutableMap<Entity, Component>

class World(
    private val inputSystems: List<InputSystem> = emptyList(),
    private val updateSystems: List<UpdateSystem> = emptyList(),
    private val renderSystems: List<RenderSystem> = emptyList(),
    val canvas: Canvas,
    val input: Input
) {
    private val _entities = mutableSetOf<Entity>()
    val entities: Set<Entity>
        get() = _entities.toSet()

    private val components: ComponentMap = mutableMapOf()

    fun addEntity(entity: Entity) {
        require(!_entities.contains(entity)) { "$entity already exists" }
        _entities += entity
    }

    fun addEntityWithComponents(entity: Entity, components: List<Component>) {
        addEntity(entity)
        components.forEach { addOrReplaceComponent(entity, it) }
    }

    fun addOrReplaceComponent(entity: Entity, component: Component) {
        check(_entities.contains(entity)) { "$entity does not exist" }

        if (!components.containsKey(component::class)) {
            components[component::class] = mutableMapOf()
        }

        components[component::class]!![entity] = component
    }

    fun <T : Component> getComponentsOfType(componentType: KClass<T>): Map<Entity, T>? {
        @Suppress("UNCHECKED_CAST")
        return components[componentType] as? Map<Entity, T>
    }

    inline fun <reified T1 : Component> forEachEntityWithComponent(action: (Entity, T1) -> Unit) {
        val componentMap = getComponentsOfType(T1::class)
        for (entity in entities) {
            val component = componentMap?.get(entity)
            if (component != null) {
                action(entity, component)
            }
        }
    }

    inline fun <reified T1 : Component, reified T2 : Component> forEachEntityWithComponents(
        action: (Entity, T1, T2) -> Unit
    ) {
        val componentMap1 = getComponentsOfType(T1::class)
        val componentMap2 = getComponentsOfType(T2::class)

        for (entity in entities) {
            val component1 = componentMap1?.get(entity)
            val component2 = componentMap2?.get(entity)
            if (component1 != null && component2 != null) {
                action(entity, component1, component2)
            }
        }
    }

    inline fun <reified T1 : Component, reified T2 : Component, reified T3 : Component> forEachEntityWithComponents(
        action: (Entity, T1, T2, T3) -> Unit
    ) {
        val componentMap1 = getComponentsOfType(T1::class)
        val componentMap2 = getComponentsOfType(T2::class)
        val componentMap3 = getComponentsOfType(T3::class)

        for (entity in entities) {
            val component1 = componentMap1?.get(entity)
            val component2 = componentMap2?.get(entity)
            val component3 = componentMap3?.get(entity)
            if (component1 != null && component2 != null && component3 != null) {
                action(entity, component1, component2, component3)
            }
        }
    }

    internal fun handleInput() {
        inputSystems
            .forEach { system ->
                system.handleInput(this)
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
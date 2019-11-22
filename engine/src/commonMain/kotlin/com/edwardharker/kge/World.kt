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

    fun addEntityWithComponents(entity: Entity = Entity.create(), components: List<Component>) {
        addEntity(entity)
        components.forEach { addOrReplaceComponent(entity, it) }
    }

    fun removeEntity(entity: Entity) {
        require(_entities.contains(entity)) { "$entity does not exist" }
        for ((_, entityComponentMap) in components) {
            entityComponentMap.remove(entity)
        }
        _entities.remove(entity)
    }

    fun removeEntities(entities: Collection<Entity>) {
        for (entity in entities.toList()) {
            removeEntity(entity)
        }
    }

    fun <T : Component> containsComponent(component: KClass<T>): Boolean {
        return !components[component].isNullOrEmpty()
    }

    fun addOrReplaceComponent(entity: Entity, component: Component) {
        check(_entities.contains(entity)) { "$entity does not exist" }

        if (!components.containsKey(component::class)) {
            components[component::class] = mutableMapOf()
        }

        components[component::class]!![entity] = component
    }

    fun removeComponent(entity: Entity, component: Component) {
        removeComponentOfType(entity, component::class)
    }

    fun <T : Component> removeComponentOfType(entity: Entity, component: KClass<T>) {
        check(_entities.contains(entity)) { "$entity does not exist" }
        check(components.containsKey(component)) {
            "There are no components of type ${component::class} in the world"
        }
        check(components[component]!!.contains(entity)) {
            "$entity does not have the component ${component::class}"
        }

        components[component]!!.remove(entity)
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

    internal fun <T1 : Component> getEntitiesWithComponent(
        component1: KClass<T1>
    ): List<EntityWithComponent1<T1>> {
        val componentMap1 = getComponentsOfType(component1)

        return entities.mapNotNull { entity ->
            val c1 = componentMap1?.get(entity)
            if (c1 != null) {
                EntityWithComponent1(
                    entity = entity,
                    component1 = c1
                )
            } else {
                null
            }
        }
    }

    internal fun <T1 : Component, T2 : Component> getEntitiesWithComponents(
        component1: KClass<T1>,
        component2: KClass<T2>
    ): List<EntityWithComponent2<T1, T2>> {
        val componentMap1 = getComponentsOfType(component1)
        val componentMap2 = getComponentsOfType(component2)

        return entities.mapNotNull { entity ->
            val c1 = componentMap1?.get(entity)
            val c2 = componentMap2?.get(entity)
            if (c1 != null && c2 != null) {
                EntityWithComponent2(
                    entity = entity,
                    component1 = c1,
                    component2 = c2
                )
            } else {
                null
            }
        }
    }

    internal fun <T1 : Component, T2 : Component, T3 : Component> getEntitiesWithComponents(
        component1: KClass<T1>,
        component2: KClass<T2>,
        component3: KClass<T3>
    ): List<EntityWithComponent3<T1, T2, T3>> {
        val componentMap1 = getComponentsOfType(component1)
        val componentMap2 = getComponentsOfType(component2)
        val componentMap3 = getComponentsOfType(component3)

        return entities.mapNotNull { entity ->
            val c1 = componentMap1?.get(entity)
            val c2 = componentMap2?.get(entity)
            val c3 = componentMap3?.get(entity)
            if (c1 != null && c2 != null && c3 != null) {
                EntityWithComponent3(
                    entity = entity,
                    component1 = c1,
                    component2 = c2,
                    component3 = c3
                )
            } else {
                null
            }
        }
    }

    inline fun <reified T1 : Component, reified T2 : Component, reified T3 : Component, reified T4 : Component> forEachEntityWithComponents(
        action: (Entity, T1, T2, T3, T4) -> Unit
    ) {
        val componentMap1 = getComponentsOfType(T1::class)
        val componentMap2 = getComponentsOfType(T2::class)
        val componentMap3 = getComponentsOfType(T3::class)
        val componentMap4 = getComponentsOfType(T4::class)

        for (entity in entities) {
            val component1 = componentMap1?.get(entity)
            val component2 = componentMap2?.get(entity)
            val component3 = componentMap3?.get(entity)
            val component4 = componentMap4?.get(entity)
            if (component1 != null &&
                component2 != null &&
                component3 != null &&
                component4 != null
            ) {
                action(entity, component1, component2, component3, component4)
            }
        }
    }

    inline fun <reified T1 : Component,
            reified T2 : Component,
            reified T3 : Component,
            reified T4 : Component,
            reified T5 : Component> forEachEntityWithComponents(
        action: (Entity, T1, T2, T3, T4, T5) -> Unit
    ) {
        val componentMap1 = getComponentsOfType(T1::class)
        val componentMap2 = getComponentsOfType(T2::class)
        val componentMap3 = getComponentsOfType(T3::class)
        val componentMap4 = getComponentsOfType(T4::class)
        val componentMap5 = getComponentsOfType(T5::class)

        for (entity in entities) {
            val component1 = componentMap1?.get(entity)
            val component2 = componentMap2?.get(entity)
            val component3 = componentMap3?.get(entity)
            val component4 = componentMap4?.get(entity)
            val component5 = componentMap5?.get(entity)
            if (component1 != null &&
                component2 != null &&
                component3 != null &&
                component4 != null &&
                component5 != null
            ) {
                action(entity, component1, component2, component3, component4, component5)
            }
        }
    }

    internal fun handleInput() {
        inputSystems
            .forEach { system ->
                system.handleInput(this)
            }
    }

    internal fun update(deltaTime: Float) {
        updateSystems
            .forEach { system ->
                system.update(this, deltaTime)
            }
    }

    internal suspend fun render() {
        canvas.startRender()
        renderSystems
            .forEach { system ->
                system.render(this)
            }
        canvas.endRender()
    }
}
package com.edwardharker.kge

import com.edwardharker.kge.canvas.Canvas
import com.edwardharker.kge.component.Component
import com.edwardharker.kge.entity.Entity
import com.edwardharker.kge.input.Input
import com.edwardharker.kge.render.CameraRenderer
import com.edwardharker.kge.render.CircleRenderer
import com.edwardharker.kge.render.RectangleRenderer
import com.edwardharker.kge.render.TextRenderer
import com.edwardharker.kge.system.CameraRenderSystem
import com.edwardharker.kge.system.CircleRenderSystem
import com.edwardharker.kge.system.CircleSpriteColliderSystem
import com.edwardharker.kge.system.CollisionSystem
import com.edwardharker.kge.system.InputSystem
import com.edwardharker.kge.system.PointerSystem
import com.edwardharker.kge.system.RectangleRenderSystem
import com.edwardharker.kge.system.RectangleSpriteColliderSystem
import com.edwardharker.kge.system.RenderDebugInfoRenderSystem
import com.edwardharker.kge.system.RenderSystem
import com.edwardharker.kge.system.TextRenderSystem
import com.edwardharker.kge.system.UpdateSystem

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class WorldMarker

@WorldMarker
class WorldBuilder(
    val canvas: Canvas = Canvas(),
    val input: Input = Input(),
    val inputSystemsBuilder: InputSystemsBuilder = InputSystemsBuilder(),
    val updateSystemsBuilder: UpdateSystemsBuilder = UpdateSystemsBuilder(),
    val renderSystemsBuilder: RenderSystemsBuilder = RenderSystemsBuilder(canvas),
    val entitiesWithComponentsBuilder: EntityWithComponentsBuilder = EntityWithComponentsBuilder()
) {
    fun inputSystems(init: InputSystemsBuilder.() -> Unit) {
        inputSystemsBuilder.init()
    }

    fun updateSystems(init: UpdateSystemsBuilder.() -> Unit) {
        updateSystemsBuilder.init()
    }

    fun renderSystems(init: RenderSystemsBuilder.() -> Unit) {
        renderSystemsBuilder.init()
    }

    fun gameObjects(init: EntityWithComponentsBuilder.() -> Unit) {
        entitiesWithComponentsBuilder.init()
    }
}

@WorldMarker
class InputSystemsBuilder(
    val inputSystems: MutableList<InputSystem> = mutableListOf()
) {
    operator fun InputSystem.unaryPlus() {
        inputSystems.add(this)
    }
}

@WorldMarker
class UpdateSystemsBuilder(
    val updateSystems: MutableList<UpdateSystem> = mutableListOf()
) {
    operator fun UpdateSystem.unaryPlus() {
        updateSystems.add(this)
    }
}

@WorldMarker
class RenderSystemsBuilder(
    val canvas: Canvas,
    val renderSystems: MutableList<RenderSystem> = mutableListOf()
) {
    operator fun RenderSystem.unaryPlus() {
        renderSystems.add(this)
    }
}

@WorldMarker
class EntityWithComponentsBuilder(
    val entitiesWithComponents: MutableList<EntityWithComponents> = mutableListOf()
) {
    fun entityWithComponents(init: EntityWithComponents.() -> Unit) {
        entitiesWithComponents.add(EntityWithComponents().apply(init))
    }

    operator fun List<Component>.unaryPlus() {
        entitiesWithComponents.add(EntityWithComponents(components = this.toMutableList()))
    }
}

data class EntityWithComponents(
    var entity: Entity = Entity.create(),
    val components: MutableList<Component> = mutableListOf()
) {
    operator fun Component.unaryPlus() {
        components.add(this)
    }

    operator fun List<Component>.unaryPlus() {
        components.addAll(this)
    }
}

fun world(init: WorldBuilder.() -> Unit): World {
    val worldBuilder = WorldBuilder().apply {
        inputSystems {
            +PointerSystem
        }

        updateSystems {
            +this@apply.input
            +RectangleSpriteColliderSystem
            +CircleSpriteColliderSystem
            +CollisionSystem
        }

        renderSystems {
            +cameraRenderSystem(canvas)
            +rectangleRenderSystem(canvas)
            +circleRenderSystem(canvas)
            +textRenderSystem(canvas)
            +RenderDebugInfoRenderSystem
        }
    }
    worldBuilder.init()
    return World(
        inputSystems = worldBuilder.inputSystemsBuilder.inputSystems,
        updateSystems = worldBuilder.updateSystemsBuilder.updateSystems,
        renderSystems = worldBuilder.renderSystemsBuilder.renderSystems,
        canvas = worldBuilder.canvas,
        input = worldBuilder.input
    ).apply {
        val entitiesWithComponents =
            worldBuilder.entitiesWithComponentsBuilder.entitiesWithComponents
        for ((entity, components) in entitiesWithComponents) {
            addEntityWithComponents(
                entity = entity,
                components = components
            )
        }
    }
}

fun rectangleRenderSystem(canvas: Canvas): RenderSystem {
    val rectangleRenderer = RectangleRenderer()
    canvas.addRenderer(rectangleRenderer)
    return RectangleRenderSystem(rectangleRenderer)
}

fun circleRenderSystem(canvas: Canvas): RenderSystem {
    val circleRenderer = CircleRenderer()
    canvas.addRenderer(circleRenderer)
    return CircleRenderSystem(circleRenderer)
}

fun textRenderSystem(canvas: Canvas): RenderSystem {
    val textRenderer = TextRenderer()
    canvas.addRenderer(textRenderer)
    return TextRenderSystem(textRenderer)
}

fun cameraRenderSystem(canvas: Canvas): RenderSystem {
    val cameraRenderer = CameraRenderer()
    canvas.addRenderer(cameraRenderer)
    return CameraRenderSystem(cameraRenderer)
}

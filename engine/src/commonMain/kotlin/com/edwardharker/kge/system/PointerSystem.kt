package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.input.PointerAction


object PointerSystem : InputSystem {
    override fun handleInput(world: World) {
        world.forEachEntityWithComponent { entity, pointer: PointerComponent ->
            when (val newPointer = world.input.primaryPointer) {
                is PointerAction.None -> {
                    world.addOrReplaceComponent(
                        entity,
                        pointer.copy(primaryPointerAction = newPointer)
                    )
                }
                is PointerAction.Down -> {
                    if (pointer.primaryPointerAction is PointerAction.None
                        || pointer.primaryPointerAction is PointerAction.Up
                    ) {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(primaryPointerAction = newPointer)
                        )
                    } else {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(
                                primaryPointerAction = PointerAction.Move(
                                    newPointer.x,
                                    newPointer.y
                                )
                            )
                        )
                    }
                }
                is PointerAction.Move -> {
                    if (pointer.primaryPointerAction is PointerAction.Down
                        || pointer.primaryPointerAction is PointerAction.Move
                    ) {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(primaryPointerAction = newPointer)
                        )
                    } else if (pointer.primaryPointerAction is PointerAction.None) {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(
                                primaryPointerAction = PointerAction.Down(
                                    newPointer.x,
                                    newPointer.y
                                )
                            )
                        )
                    } else {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(primaryPointerAction = PointerAction.None)
                        )
                    }
                }
                is PointerAction.Up -> {
                    if (pointer.primaryPointerAction is PointerAction.Down
                        || pointer.primaryPointerAction is PointerAction.Move
                    ) {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(primaryPointerAction = newPointer)
                        )
                    } else {
                        world.addOrReplaceComponent(
                            entity,
                            pointer.copy(primaryPointerAction = PointerAction.None)
                        )
                    }
                }
            }
        }
    }
}
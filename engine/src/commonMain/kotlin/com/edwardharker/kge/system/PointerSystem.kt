package com.edwardharker.kge.system

import com.edwardharker.kge.World
import com.edwardharker.kge.component.PointerComponent
import com.edwardharker.kge.input.PointerAction

object PointerSystem : InputSystem {
    override fun handleInput(world: World) {
        val newPointer = world.input.primaryPointer
        world.forEachEntityWithComponent { entity, pointer: PointerComponent ->
            val sanitizedPointerAction = when (val oldPointer = pointer.primaryPointerAction) {
                is PointerAction.Up -> transitionFromUp(oldPointer, newPointer)
                is PointerAction.Down -> transitionFromDown(oldPointer, newPointer)
                is PointerAction.Move -> transitionFromMove(oldPointer, newPointer)
                is PointerAction.None -> transitionFromNone(oldPointer, newPointer)
            }
            world.addOrReplaceComponent(
                entity = entity,
                component = PointerComponent(primaryPointerAction = sanitizedPointerAction)
            )
        }
    }

    private fun transitionFromNone(
        oldPointer: PointerAction.None,
        newPointer: PointerAction
    ): PointerAction {
        return when (newPointer) {
            is PointerAction.Up -> oldPointer
            is PointerAction.Down -> newPointer
            is PointerAction.Move -> oldPointer
            is PointerAction.None -> oldPointer
        }
    }

    private fun transitionFromDown(
        oldPointer: PointerAction.Down,
        newPointer: PointerAction
    ): PointerAction {
        return when (newPointer) {
            is PointerAction.Up -> newPointer
            is PointerAction.Down -> PointerAction.Move(oldPointer.x, oldPointer.y)
            is PointerAction.Move -> newPointer
            is PointerAction.None -> PointerAction.Up(oldPointer.x, oldPointer.y)
        }
    }

    private fun transitionFromMove(
        oldPointer: PointerAction.Move,
        newPointer: PointerAction
    ): PointerAction {
        return when (newPointer) {
            is PointerAction.Up -> newPointer
            is PointerAction.Down -> oldPointer
            is PointerAction.Move -> oldPointer
            is PointerAction.None -> PointerAction.Up(oldPointer.x, oldPointer.y)
        }
    }

    private fun transitionFromUp(
        oldPointer: PointerAction.Up,
        newPointer: PointerAction
    ): PointerAction {
        return when (newPointer) {
            is PointerAction.Up -> PointerAction.None
            is PointerAction.Down -> newPointer
            is PointerAction.Move -> PointerAction.Down(oldPointer.x, oldPointer.y)
            is PointerAction.None -> newPointer
        }
    }
}

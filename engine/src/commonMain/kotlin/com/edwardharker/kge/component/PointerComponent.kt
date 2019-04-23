package com.edwardharker.kge.component

import com.edwardharker.kge.input.PointerAction

data class PointerComponent(
    val primaryPointerAction: PointerAction = PointerAction.None
) : Component
package com.edwardharker.kge

import com.edwardharker.kge.component.Component
import com.edwardharker.kge.entity.Entity

data class EntityWithComponent3<T1 : Component, T2 : Component, T3 : Component>(
    val entity: Entity,
    val component1: T1,
    val component2: T2,
    val component3: T3
)

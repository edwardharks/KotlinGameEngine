package com.edwardharker.kge.entity

inline class Entity(val id: Int) {
    companion object {
        private var nextId = Int.MIN_VALUE
        fun create(): Entity {
            return Entity(++nextId)
        }
    }
}

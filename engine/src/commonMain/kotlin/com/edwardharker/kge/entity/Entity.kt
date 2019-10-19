package com.edwardharker.kge.entity

inline class Entity(val id: Long) {
    companion object {
        private var nextId = Long.MIN_VALUE
        fun create(): Entity {
            return Entity(++nextId)
        }
    }
}

package com.edwardharker.kge.system

fun cameraSystem(): List<UpdateSystem> {
    return listOf(SingleCameraSystem)
}
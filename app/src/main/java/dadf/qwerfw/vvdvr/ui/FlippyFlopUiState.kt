package com.flappies.flippyflop.ui

data class FlippyFlopUiState (
    val characterCoordinateStartY: Float = 0f,
    val characterCoordinateY: Float = 0f,
    val characterVelocity: Float = 0f,
    val characterRotationZ: Float = 0f,
    val characterFallingTime: Float = 0f,
    val backgroundOffset: Int = 0
)
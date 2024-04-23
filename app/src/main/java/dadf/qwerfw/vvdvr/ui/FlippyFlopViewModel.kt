package com.flappies.flippyflop.ui.theme

import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flappies.flippyflop.R
import com.flappies.flippyflop.ui.FlippyFlopUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.random.Random

class FlippyFlopViewModel: ViewModel() {
    val uiState = MutableStateFlow(FlippyFlopUiState())

    // phys settings (off-system units)
    val physicsAccuracy: Float = 60f
    val acceleration: Float = 1700f
    val startVelocity: Float = 500f
    val angularVelocity: Float = 0.4f

    // movement setting
    val movementRate: Long = 17
    val movementColumns: Int = 5
    val movementSpringRate: Long = 68

    // uiState optimize
    var gameContinues = false

    var obstacleCoordinateX = 0f
    var obstacleCoordinateY = 0
    var obstacleLimitStartY = 0
    var obstacleLimitEndY = 0
    var tubeDrawable = R.drawable.tube1
    var score = -1

    var backgroundNull = false
    var clicked = false

    fun startPhysics(action: () -> Unit) {
        viewModelScope.launch {
            delay(1000 / physicsAccuracy.toLong())

            if (
                gameContinues &&
                obstacleCoordinateX in -146f - 20..100f - 10 &&
                uiState.value.characterCoordinateY !in obstacleLimitStartY.toFloat() - 15..obstacleLimitEndY.toFloat() + 15
            ) {
                action()
                changeGameState()
            }

            uiState.update {
                it.copy(
                    characterFallingTime = if (clicked) 0.05f else uiState.value.characterFallingTime + 1 / physicsAccuracy,
                    characterCoordinateY = uiState.value.characterCoordinateStartY -
                            startVelocity * uiState.value.characterFallingTime +
                                acceleration / 2 * uiState.value.characterFallingTime.pow(2),
                    characterVelocity = -startVelocity + acceleration * uiState.value.characterFallingTime,
                    characterRotationZ = if (clicked) 0f else uiState.value.characterRotationZ + angularVelocity,
                    backgroundOffset = if (backgroundNull) 0 else uiState.value.backgroundOffset - 1,
                    characterCoordinateStartY = if (clicked) uiState.value.characterCoordinateY else uiState.value.characterCoordinateStartY
                )
            }

            if (gameContinues) {
                backgroundNull = false
                clicked = false
                startPhysics(action)
            }
        }
    }

    fun backgroundOffsetToZero() {
        backgroundNull = true
    }

    fun click() {
        clicked = true
    }

    fun clearScore() {
        score = -1
    }

    fun changeGameState() {
        gameContinues = !gameContinues
        uiState.update {
            it.copy(
                characterFallingTime = 0f,
                characterCoordinateStartY = 0f,
                characterRotationZ = 0f
            )
        }
    }

    fun setObstacleStartPosition(configuration: Configuration) {
        val y = Random.nextInt(-configuration.screenHeightDp / 2 + 194 / 2, configuration.screenHeightDp / 2 - 194 / 2)  // 303
        obstacleCoordinateY = y
        obstacleCoordinateX = configuration.screenWidthDp * 1.35f
        obstacleLimitStartY = y - 194 / 2 + 50
        obstacleLimitEndY = y + 194 / 2 - 50
        score += 1
    }

    fun moveObstacle(configuration: Configuration) {
        viewModelScope.launch {
            if (obstacleCoordinateX < -146) {
                setObstacleStartPosition(configuration)
            }
            obstacleCoordinateX -= movementColumns
            delay(movementRate)
            if (gameContinues)
                moveObstacle(configuration)
        }
    }

    val drawables = listOf(
        R.drawable.tube1,
        R.drawable.tube2,
        R.drawable.tube3
    )
    var nowIndex: Int = -1
    fun moveSpring() {
        viewModelScope.launch {
            nowIndex = nowIndex.inc() % 6
            tubeDrawable = if (nowIndex in 0..2) drawables[nowIndex] else drawables[5 - nowIndex]
            delay(movementSpringRate)
            if (gameContinues) {
                moveSpring()
            }
        }
    }
}
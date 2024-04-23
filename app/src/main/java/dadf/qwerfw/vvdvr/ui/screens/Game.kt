package com.flappies.flippyflop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.flappies.flippyflop.R
import com.flappies.flippyflop.ui.FlippyFlopUiState
import com.flappies.flippyflop.ui.theme.FlippyFlopViewModel

@Composable
fun Game(
    viewModel: FlippyFlopViewModel,
    uiState: FlippyFlopUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable (interactionSource = remember { MutableInteractionSource() }, indication = null) {
                viewModel.click()
            }
    ) {
        val density = LocalDensity.current
        Image(
            painter = painterResource(if (uiState.characterVelocity > 0) R.drawable.ball else R.drawable.ball),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.character))
                .graphicsLayer {
                    rotationZ = uiState.characterRotationZ
                    translationY = with(density) { uiState.characterCoordinateY.dp.toPx() }
                }
                .align(Alignment.CenterStart)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize(unbounded = true)
                .offset(
                    x = viewModel.obstacleCoordinateX.dp,
                    y = viewModel.obstacleCoordinateY.dp
                )
        ) {
            Spacer(
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp)
                    .width(dimensionResource(R.dimen.outline))
                    .background(Color.Black)
            )
            Image(
                painter = painterResource(viewModel.tubeDrawable),
                contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(R.dimen.obstacle)),
                contentScale = ContentScale.FillHeight
            )
            Spacer(
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp)
                    .width(dimensionResource(R.dimen.outline))
                    .background(Color.Black)
            )
        }
    }
}
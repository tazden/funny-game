package com.flappies.flippyflop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.flappies.flippyflop.R
import com.flappies.flippyflop.ui.Destinations
import com.flappies.flippyflop.ui.theme.FlippyFlopViewModel
import com.flappies.flippyflop.ui.theme.Transparent

@Composable
fun End(
    viewModel: FlippyFlopViewModel,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Transparent)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.simple_padding))
                .size(dimensionResource(R.dimen.end_card))
                .align(Alignment.Center)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.shapes.extraSmall
                )
                .padding(dimensionResource(R.dimen.simple_padding))
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                stringResource(R.string.game_over),
                style = MaterialTheme.typography.displayMedium
            )
            Row {
                Image(
                    painter = painterResource(R.drawable.flop1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.character) * 1.5f)
                )
                Column {
                    Text(
                        text = stringResource(R.string.score) + "   " + viewModel.score,
                        style = MaterialTheme.typography.bodySmall
                    )
                    val configuration = LocalConfiguration.current
                    Row {
                        Button(
                            onClick = {
                                navController.navigate(Destinations.Game.name)
                                viewModel.changeGameState()
                                viewModel.clearScore()
                                viewModel.setObstacleStartPosition(configuration)
                                viewModel.startPhysics { navController.navigate(Destinations.End.name) }
                                viewModel.moveObstacle(configuration)
                                viewModel.moveSpring()
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Refresh,
                                contentDescription = null,
                                Modifier.size(dimensionResource(R.dimen.simple_padding) * 1.5f)
                            )
                        }
                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.simple_padding)))
                        Button(
                            onClick = {
                                navController.navigate(Destinations.Menu.name)
                                viewModel.clearScore()
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Home,
                                contentDescription = null,
                                Modifier.size(dimensionResource(R.dimen.simple_padding) * 1.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}
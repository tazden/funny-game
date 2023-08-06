package com.flappies.flippyflop.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flappies.flippyflop.ui.screens.End
import com.flappies.flippyflop.ui.screens.Game
import com.flappies.flippyflop.ui.screens.Menu
import com.flappies.flippyflop.ui.screens.MovableBackground
import com.flappies.flippyflop.ui.theme.FlippyFlopViewModel

enum class Destinations {
    Menu,
    Game,
    End
}

@Composable
fun FlippyFlopApp(
    viewModel: FlippyFlopViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .wrapContentSize(unbounded = true)
    ) {
        MovableBackground(uiState, viewModel)
    }

    NavHost(
        navController = navController,
        startDestination = Destinations.Menu.name
    ) {
        composable(route = Destinations.Menu.name) {
            Menu(viewModel, navController)
        }
        composable(route = Destinations.Game.name) {
            Game(viewModel, uiState)
        }
        composable(route = Destinations.End.name) {
            End(viewModel, navController)
        }
    }
}
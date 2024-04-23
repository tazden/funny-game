package dadf.qwerfw.vvdvr.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import dadf.qwerfw.vvdvr.R
import dadf.qwerfw.vvdvr.ui.Destinations
import dadf.qwerfw.vvdvr.ui.theme.FlippyFlopViewModel
import dadf.qwerfw.vvdvr.ui.theme.Transparent

@Composable
fun Menu(
    viewModel: FlippyFlopViewModel,
    navController: NavHostController
) {
    Box {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(Transparent)
        )

        Text(
            stringResource(R.string.flippy_flop),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(dimensionResource(R.dimen.simple_padding))
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.4f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val configuration = LocalConfiguration.current
            IconButton(
                onClick = {
                    navController.navigate(Destinations.Game.name)
                    viewModel.changeGameState()
                    viewModel.setObstacleStartPosition(configuration)
                    viewModel.startPhysics { navController.navigate(Destinations.End.name) }
                    viewModel.moveObstacle(configuration)
                    viewModel.moveSpring()
                },
                modifier = Modifier
                    .size(dimensionResource(R.dimen.play_button_size))
            ) {
                Icon(
                    Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.play_button_size)),
                    tint = MaterialTheme.colorScheme.surfaceVariant
                )
            }
            Row(

            ) {
                Button(
                    onClick = {

                    }
                ) {
                    Text(text = stringResource(R.string.english))
                }
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.simple_padding)))
                Button(
                    onClick = {

                    }
                ) {
                    Text(text = stringResource(R.string.russian))
                }
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.simple_padding) * 2))
        }
    }
}
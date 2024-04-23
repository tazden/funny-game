package dadf.qwerfw.vvdvr.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dadf.qwerfw.vvdvr.R
import dadf.qwerfw.vvdvr.ui.FlippyFlopUiState
import dadf.qwerfw.vvdvr.ui.theme.FlippyFlopViewModel

@Composable
fun MovableBackground(
    uiState: FlippyFlopUiState,
    viewModel: FlippyFlopViewModel
) {
    val size = maxOf(
        LocalConfiguration.current.screenWidthDp.dp,
        LocalConfiguration.current.screenHeightDp.dp
    )

    if (uiState.backgroundOffset.dp <= -size) {
        viewModel.backgroundOffsetToZero()
    }

    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .offset(uiState.backgroundOffset.dp)
    )

    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .offset(size + uiState.backgroundOffset.dp)
    )
}
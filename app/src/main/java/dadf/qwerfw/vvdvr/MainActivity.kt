package dadf.qwerfw.vvdvr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dadf.qwerfw.vvdvr.ui.FlippyFlopApp
import dadf.qwerfw.vvdvr.ui.theme.FlippyFlopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlippyFlopTheme {
                FlippyFlopApp()
            }
        }
    }
}
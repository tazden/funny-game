package com.flappies.flippyflop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.flappies.flippyflop.ui.FlippyFlopApp
import com.flappies.flippyflop.ui.theme.FlippyFlopTheme

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
package com.app.pexels.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColor(
    color: Color,
    isDarkIcons: Boolean = false,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = isDarkIcons,
        )
    }
}
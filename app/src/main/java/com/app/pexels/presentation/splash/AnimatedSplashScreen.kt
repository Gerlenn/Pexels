package com.app.pexels.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.app.pexels.ui.theme.SetStatusBarColor
import kotlinx.coroutines.delay

private const val DELAY_TIME = 3000L

@Composable
fun AnimatedSplashScreen(
    navigateOnBoarding: () -> Unit,
    navigateBack: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        delay(DELAY_TIME)
        navigateBack()
        navigateOnBoarding()
    }

    SetStatusBarColor(color = Color.Transparent)

    SplashScreen()
}

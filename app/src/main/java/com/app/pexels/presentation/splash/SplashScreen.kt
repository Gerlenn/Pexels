package com.app.pexels.presentation.splash

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.pexels.R
import com.app.pexels.presentation.splash.AnimationConstants.ALPHA_INIT_VALUE
import com.app.pexels.presentation.splash.AnimationConstants.ALPHA_TARGET_VALUE
import com.app.pexels.presentation.splash.AnimationConstants.ANIMATION_DURATION
import com.app.pexels.presentation.splash.AnimationConstants.SCALE_INIT_VALUE
import com.app.pexels.presentation.splash.AnimationConstants.SCALE_TARGET_VALUE
import com.app.pexels.ui.theme.DarkRed
import com.app.pexels.ui.theme.White

@Composable
fun SplashScreen() {
    val alphaLogo by rememberInfiniteTransition().animateFloat(
        initialValue = ALPHA_INIT_VALUE,
        targetValue = ALPHA_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ANIMATION_DURATION),
        ),
    )

    val scaleLogo by rememberInfiniteTransition().animateFloat(
        initialValue = SCALE_INIT_VALUE,
        targetValue = SCALE_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ANIMATION_DURATION),
        ),
    )

    Box(
        modifier = Modifier
            .background(DarkRed)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .scale(scaleLogo)
                .alpha(alphaLogo),
            imageVector = ImageVector.vectorResource(id = R.drawable.splash_logo),
            contentDescription = null,
            tint = White,
        )
    }
}

private object AnimationConstants {
    const val ALPHA_INIT_VALUE = 0f
    const val ALPHA_TARGET_VALUE = 1f
    const val SCALE_INIT_VALUE = 0f
    const val SCALE_TARGET_VALUE = 1.0f
    const val ANIMATION_DURATION = 3000
}

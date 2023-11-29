package com.app.pexels.presentation.onboarding.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.pexels.ui.theme.LightOrange
import com.app.pexels.ui.theme.LightYellow

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = 15.dp)

    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) LightOrange else LightYellow.copy(alpha = 0.5f)),
    )
}

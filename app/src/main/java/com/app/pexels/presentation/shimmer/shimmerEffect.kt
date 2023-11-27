package com.app.pexels.presentation.shimmer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.app.pexels.ui.theme.shimmerColor

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = INITIAL_OFFSET_X * size.width.toFloat(),
        targetValue = TARGET_OFFSET_X * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(ANIMATION_DURATION))
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColor,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
            size = it.size
        }
}

private const val INITIAL_OFFSET_X = -2
private const val TARGET_OFFSET_X = 2
private const val ANIMATION_DURATION = 1000
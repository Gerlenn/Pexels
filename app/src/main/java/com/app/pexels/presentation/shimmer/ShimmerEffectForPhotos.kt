package com.app.pexels.presentation.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffectForPhotos(
    modifier: Modifier = Modifier,
    isDataLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
) {

    if (isDataLoading) Box(
        modifier = modifier
            .size(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .shimmerEffect(),
    ) else contentAfterLoading()
}

package com.app.pexels.presentation.details.image

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

@Composable
fun MainImage(
    modifier: Modifier = Modifier,
    photoUrl: String,
) {
    var scale by remember { mutableFloatStateOf(SCALE_DELTA) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    BoxWithConstraints(
        modifier = modifier.clip(RoundedCornerShape(16.dp))
    ) {
        val transformableState = rememberTransformableState { zoomChange, panChange, _ ->
            scale = calculateNewScale(zoomChange, scale)
            offset = calculateNewOffset(zoomChange, panChange, scale, offset, constraints)
        }

        LaunchedEffect(transformableState.isTransformInProgress) {
            if (!transformableState.isTransformInProgress) {
                val scaleAnimation = async {
                    animateToTarget(scale, SCALE_DELTA) { value ->
                        scale = value
                    }
                }
                val offsetHorizontalAnimation = async {
                    animateToTarget(offset.x, ZERO_OFFSET) { value ->
                        offset = Offset(value, offset.y)
                    }
                }
                val offsetVerticalAnimation = async {
                    animateToTarget(offset.y, ZERO_OFFSET) { value ->
                        offset = Offset(offset.x, value)
                    }
                }
                awaitAll(scaleAnimation, offsetHorizontalAnimation, offsetVerticalAnimation)
            }
        }

        AsyncImage(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(transformableState),
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

private suspend fun animateToTarget(
    initialValue: Float,
    targetValue: Float,
    onUpdate: (Float) -> Unit,
) {
    animate(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION_MILLIS,
            easing = LinearOutSlowInEasing
        )
    ) { value, _ ->
        onUpdate(value)
    }
}

private fun calculateNewScale(
    zoomChange: Float,
    currentScale: Float,
): Float = (currentScale * zoomChange).coerceIn(MIN_SCALE, MAX_SCALE)

private fun calculateNewOffset(
    zoomChange: Float,
    panChange: Offset,
    currentScale: Float,
    currentOffset: Offset,
    constraints: Constraints,
): Offset {
    val newScale = calculateNewScale(zoomChange, currentScale)
    val deltaScale = newScale - SCALE_DELTA
    val extraWidth = deltaScale * constraints.maxWidth
    val extraHeight = deltaScale * constraints.maxHeight
    val maxCoordinatesX = extraWidth * MAX_COORDINATES_FACTOR
    val maxCoordinatesY = extraHeight * MAX_COORDINATES_FACTOR
    return Offset(
        x = (currentOffset.x + newScale * panChange.x).coerceIn(-maxCoordinatesX, maxCoordinatesX),
        y = (currentOffset.y + newScale * panChange.y).coerceIn(-maxCoordinatesY, maxCoordinatesY)
    )
}

private const val MAX_SCALE = 5f
private const val MIN_SCALE = 1f
private const val MAX_COORDINATES_FACTOR = 0.5f
private const val SCALE_DELTA = 1f
private const val ZERO_OFFSET = 0f
private const val ANIMATION_DURATION_MILLIS = 700

@Preview()
@Composable
fun PreviewMainImage() {
    MainImage(
        modifier = Modifier,
        photoUrl = ""
    )
}

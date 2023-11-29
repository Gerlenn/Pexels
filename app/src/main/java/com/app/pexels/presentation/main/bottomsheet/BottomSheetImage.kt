package com.app.pexels.presentation.main.bottomsheet

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BottomSheetImage(image: Int) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))

    LottieAnimation(
        modifier = Modifier.height(150.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

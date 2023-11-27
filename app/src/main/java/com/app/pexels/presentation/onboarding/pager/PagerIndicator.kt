package com.app.pexels.presentation.onboarding.pager

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun PagerIndicator(
    size: Int,
    currentPage: Int,
) {
    Row {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

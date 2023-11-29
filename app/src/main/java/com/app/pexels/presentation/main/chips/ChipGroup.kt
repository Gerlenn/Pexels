package com.app.pexels.presentation.main.chips

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pexels.presentation.model.ChipUi

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    chips: List<ChipUi>,
    onChipSelected: (String) -> Unit,
    chipText: String,
) {

    LazyRow(modifier = modifier) {
        items(chips) { chip ->
            val isSelected = chip.title == chipText
            Chip(
                title = chip.title,
                onChipSelected = { title ->
                    onChipSelected(title)
                },
                isSelected = isSelected,
            )
        }
    }
}

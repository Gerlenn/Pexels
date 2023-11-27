package com.app.pexels.presentation.details.bottom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.pexels.presentation.details.bottom.buttons.BookmarkButton
import com.app.pexels.presentation.details.bottom.buttons.DownloadButton

@Composable
fun BottomButtons(
    modifier: Modifier = Modifier,
    onDownloadClicked: () -> Unit,
    onBookmarkClicked: () -> Unit,
    isPhotoInFavorites: Boolean,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DownloadButton(onDownloadClicked = onDownloadClicked)
        BookmarkButton(
            onBookmarkClicked = onBookmarkClicked,
            isPhotoInFavorites = isPhotoInFavorites
        )
    }
}

@Preview()
@Composable
fun PreviewBottomButtons() {
    BottomButtons(
        modifier = Modifier,
        onDownloadClicked = {},
        onBookmarkClicked = {},
        isPhotoInFavorites = false
    )
}

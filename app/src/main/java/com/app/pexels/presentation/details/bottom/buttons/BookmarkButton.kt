package com.app.pexels.presentation.details.bottom.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pexels.R

@Composable
fun BookmarkButton(
    modifier: Modifier = Modifier,
    onBookmarkClicked: () -> Unit,
    isPhotoInFavorites: Boolean,
) {

    var isBookmarkActive by remember { mutableStateOf(isPhotoInFavorites) }

    Box(modifier = modifier
        .size(48.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.surface)
        .clickable {
            onBookmarkClicked()
            isBookmarkActive = !isBookmarkActive
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(18.dp, 22.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (isBookmarkActive) {
                Image(
                    painter = painterResource(R.drawable.ic_bookmark_active),
                    contentDescription = null,
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_bookmark_inactive),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary)
                )
            }
        }
    }
}

@Preview()
@Composable
fun PreviewBookmarkButton() {
    BookmarkButton(
        modifier = Modifier,
        onBookmarkClicked = {},
        isPhotoInFavorites = false
    )
}

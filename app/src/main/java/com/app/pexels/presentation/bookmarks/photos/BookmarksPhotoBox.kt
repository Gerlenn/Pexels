package com.app.pexels.presentation.bookmarks.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.pexels.ui.theme.CustomMulishW400
import com.app.pexels.ui.theme.White

@Composable
fun BookmarksPhotoBox(
    modifier: Modifier = Modifier,
    photoUrl: String,
    photographer: String,
    onPhotoClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .clickable(onClick = {
                    onPhotoClicked()
                })
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(33.dp)
                .background(Black.copy(alpha = 0.4f))
                .padding(
                    start = 20.dp,
                    end = 20.dp
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = photographer,
                color = White,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontFamily = CustomMulishW400,
            )
        }

    }
}

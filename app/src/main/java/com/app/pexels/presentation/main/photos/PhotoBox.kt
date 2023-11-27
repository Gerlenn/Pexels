package com.app.pexels.presentation.main.photos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pexels.R


@Composable
fun PhotoBox(
    modifier: Modifier = Modifier,
    photoUrl: String,
    onPhotoClicked: () -> Unit,
) {

    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = {
                onPhotoClicked()
            }),
        model = photoUrl,
        contentDescription = null,
        contentScale = ContentScale.Inside,
        placeholder = painterResource(R.drawable.ic_place_holder)
    )
}

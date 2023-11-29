package com.app.pexels.presentation.details.bottom.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.ui.theme.CustomMulishW600

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    onDownloadClicked: () -> Unit,
) {

    Box(
        modifier = modifier
            .size(180.dp, 48.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onDownloadClicked()
            }
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Red),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_red_circle_download),
                contentDescription = null,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 17.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.download),
                color = MaterialTheme.colorScheme.inversePrimary,
                fontFamily = CustomMulishW600,
                fontSize = 14.sp
            )
        }
    }
}

@Preview()
@Composable
fun PreviewDownloadButton() {
    DownloadButton(
        modifier = Modifier,
        onDownloadClicked = {},
    )
}

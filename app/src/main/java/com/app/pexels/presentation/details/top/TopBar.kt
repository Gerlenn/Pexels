package com.app.pexels.presentation.details.top

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.ui.theme.CustomMulishW700

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    photographer: String,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .height(40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            ) {
                IconButton(onClick = { onBackClicked() }) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary)
                    )
                }
            }
            Text(
                text = photographer,
                fontFamily = CustomMulishW700,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Spacer(modifier = Modifier.width(50.dp))
        }
    }
}

@Preview()
@Composable
fun PreviewTopBar() {
    TopBar(
        modifier = Modifier,
        onBackClicked = {},
        photographer = ""
    )
}

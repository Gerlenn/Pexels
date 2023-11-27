package com.app.pexels.presentation.main.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.ui.theme.CustomMulishW400
import com.app.pexels.ui.theme.White

@Composable
fun Chip(
    title: String,
    onChipSelected: (String) -> Unit,
    isSelected: Boolean,
) {

    val background = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val contentColor = if (isSelected) White else MaterialTheme.colorScheme.inversePrimary

    Box(
        modifier = Modifier
            .padding(end = 11.dp)
            .height(38.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    onChipSelected(title)
                }
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                )
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                color = contentColor,
                fontSize = 14.sp,
                fontFamily = CustomMulishW400,
            )
        }
    }
}

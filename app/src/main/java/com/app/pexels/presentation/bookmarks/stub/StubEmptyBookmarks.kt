package com.app.pexels.presentation.bookmarks.stub

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.ui.theme.CustomMulishW400
import com.app.pexels.ui.theme.CustomMulishW700

@Composable
fun StubEmptyBookmarks(
    modifier: Modifier = Modifier,
    onExploreClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = stringResource(id = R.string.nothing_saved),
                color = MaterialTheme.colorScheme.inversePrimary,
                fontSize = 14.sp,
                fontFamily = CustomMulishW400,
            )
            Text(
                modifier = Modifier.clickable(
                    onClick = {
                        onExploreClick()
                    }
                ),
                text = stringResource(id = R.string.explore),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontFamily = CustomMulishW700,
                )
            )
        }
    }
}

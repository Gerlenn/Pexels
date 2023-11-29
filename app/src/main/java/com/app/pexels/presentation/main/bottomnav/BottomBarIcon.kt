package com.app.pexels.presentation.main.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.pexels.R
import com.app.pexels.presentation.navigation.NavigationItem

@Composable
fun BottomBarIcon(
    isSelectedIcon: Boolean,
    item: NavigationItem,
) {
    Column {
        Column {
            if (isSelectedIcon) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_selector),
                    contentDescription = null,
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = if (isSelectedIcon) item.selectedIcon else item.unselectedIcon,
                contentDescription = null,
            )
        }
    }
}

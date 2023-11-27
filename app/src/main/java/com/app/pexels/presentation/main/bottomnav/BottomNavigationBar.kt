package com.app.pexels.presentation.main.bottomnav

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.pexels.R
import com.app.pexels.presentation.navigation.NavigationItem

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navigateToScreen: () -> Unit,
    screenIndex: Int,
    getPopularPhotos: () -> Unit,
) {

    val items = listOf(
        NavigationItem(
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_home_selected),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ic_home_unselected)
        ),
        NavigationItem(
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_bookmark_selected),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ic_bookmark_unselected)
        )
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
                items.forEachIndexed { currentIndex, item ->
                    val isSelectedIcon = screenIndex == currentIndex
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            when {
                                screenIndex != currentIndex -> navigateToScreen()
                                screenIndex == 0 -> getPopularPhotos()
                            }
                        },
                        icon = {
                            BottomBarIcon(isSelectedIcon, item)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Spacer(modifier = Modifier.padding(paddingValues))
    }
}

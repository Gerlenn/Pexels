package com.app.pexels.presentation.navigation.destination

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.app.pexels.presentation.navigation.Screen

fun navigateFromMainToBookmarks(navController: NavHostController) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(Screen.Main.route, true)
        .build()

    navController.navigate(Screen.Bookmarks.route, navOptions)
}
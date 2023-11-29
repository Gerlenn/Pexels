package com.app.pexels.presentation.navigation.destination

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.app.pexels.presentation.navigation.Screen

fun navigateToMain(navController: NavHostController) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(Screen.OnBoarding.route, true)
        .build()

    navController.navigate(Screen.Main.route, navOptions)
}
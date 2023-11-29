package com.app.pexels.presentation.navigation.destination

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.app.pexels.presentation.navigation.Screen

fun navigateToOnboarding(navController: NavHostController) {
    val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(Screen.Splash.route, true)
        .build()

    navController.navigate(Screen.OnBoarding.route, navOptions)
}
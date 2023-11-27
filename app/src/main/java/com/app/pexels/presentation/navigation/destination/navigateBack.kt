package com.app.pexels.presentation.navigation.destination

import androidx.navigation.NavHostController

fun navigateBack(navController: NavHostController) {
    navController.popBackStack()
}
package com.app.pexels.presentation.navigation.destination

import androidx.navigation.NavHostController
import com.app.pexels.presentation.navigation.Screen
import com.app.pexels.util.encodeUrl

fun navigateToDetails(navController: NavHostController, photoUrl: String, photographer: String) {

    navController.navigate("${Screen.Details.route}/${photoUrl.encodeUrl()}/$photographer")
}
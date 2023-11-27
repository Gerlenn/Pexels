package com.app.pexels.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(SPLASH_SCREEN)
    object OnBoarding : Screen(ONBOARDING_SCREEN)
    object Main : Screen(MAIN_SCREEN)
    object Details : Screen(DETAILS_SCREEN)
    object Bookmarks : Screen(BOOKMARKS_SCREEN)

    private companion object ScreenRoute {
        const val SPLASH_SCREEN = "splashScreen"
        const val ONBOARDING_SCREEN = "onboardingScreen"
        const val MAIN_SCREEN = "mainScreen"
        const val DETAILS_SCREEN = "detailsScreen"
        const val BOOKMARKS_SCREEN = "bookmarksScreen"
    }
}

package com.app.pexels.presentation.navigation

interface NavigationActions {

    object NavigateMainScreen : NavigationActions
    object NavigateBack : NavigationActions
    object OnboardingFinished : NavigationActions
}
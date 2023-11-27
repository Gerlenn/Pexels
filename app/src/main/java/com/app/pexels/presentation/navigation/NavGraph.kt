@file:OptIn(ExperimentalCoroutinesApi::class)

package com.app.pexels.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.pexels.App
import com.app.pexels.presentation.bookmarks.BookmarksScreen
import com.app.pexels.presentation.bookmarks.BookmarksViewModel
import com.app.pexels.presentation.details.DetailsScreen
import com.app.pexels.presentation.details.DetailsViewModel
import com.app.pexels.presentation.main.MainScreen
import com.app.pexels.presentation.main.MainViewModel
import com.app.pexels.presentation.navigation.destination.navigateBack
import com.app.pexels.presentation.navigation.destination.navigateFromBookmarksToMain
import com.app.pexels.presentation.navigation.destination.navigateFromMainToBookmarks
import com.app.pexels.presentation.navigation.destination.navigateToDetails
import com.app.pexels.presentation.navigation.destination.navigateToDetailsWithPhotoUrl
import com.app.pexels.presentation.navigation.destination.navigateToMain
import com.app.pexels.presentation.navigation.destination.navigateToOnboarding
import com.app.pexels.presentation.onboarding.OnBoardingViewModel
import com.app.pexels.presentation.onboarding.OnboardingScreen
import com.app.pexels.presentation.splash.AnimatedSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph() {

    val navController = rememberNavController()
    val onBoardingViewModel = viewModel<OnBoardingViewModel>(factory = App.appComponent.onBoardingViewModelFactory())
    val mainViewModel = viewModel<MainViewModel>(factory = App.appComponent.mainViewModelFactory())
    val detailsViewModel = viewModel<DetailsViewModel>(factory = App.appComponent.detailsViewModelFactory())
    val bookmarksViewModel = viewModel<BookmarksViewModel>(factory = App.appComponent.bookmarksViewModelFactory())

    val isOnboardingFinished by onBoardingViewModel.isOnboardingFinished.collectAsStateWithLifecycle()
    val photos = mainViewModel.pagingData.collectAsLazyPagingItems()
    val popularCollections by mainViewModel.popularCollections.collectAsStateWithLifecycle()
    val isDataLoading by mainViewModel.isDataLoading.collectAsStateWithLifecycle()
    val isInternetConnectionAvailable by mainViewModel.isInternetConnectionAvailable.collectAsStateWithLifecycle()
    val isSearchCalled by mainViewModel.isSearchCalled.collectAsStateWithLifecycle()
    val responseCode by mainViewModel.responseCodes.collectAsStateWithLifecycle()
    val chipTitle by mainViewModel.chipTitle.collectAsStateWithLifecycle()
    val isDownloadSuccessful by detailsViewModel.isDownloadSuccessful.collectAsStateWithLifecycle()
    val isPhotoInFavorites by detailsViewModel.isPhotoInFavorites.collectAsStateWithLifecycle()
    val bookmarksPhotos by bookmarksViewModel.bookmarksPhotos.collectAsStateWithLifecycle()
    val isRefreshDataSuccess by mainViewModel.isRefreshDataSuccess.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(
                navigateOnBoarding = {
                    when (isOnboardingFinished) {
                        true -> {
                            navigateToMain(navController)
                        }

                        false -> {
                            navigateToOnboarding(navController)
                        }
                    }
                },
                navigateBack = { navigateBack(navController) },
            )
        }
        composable(route = Screen.OnBoarding.route) {
            Surface(modifier = Modifier.fillMaxSize()) {
                OnboardingScreen { action ->
                    when (action) {
                        NavigationActions.NavigateMainScreen -> {
                            navigateToMain(navController)
                        }

                        NavigationActions.NavigateBack -> {
                            navigateBack(navController)
                        }

                        NavigationActions.OnboardingFinished -> {
                            onBoardingViewModel.onBoardingFinished()
                        }
                    }
                }
            }
        }
        composable(route = Screen.Main.route) {
            MainScreen(
                photos = photos,
                chips = popularCollections,
                onSearch = mainViewModel::getSearchPhotos,
                isDataLoading = isDataLoading,
                refreshData = {
                    mainViewModel.getCuratedPhotos()
                    mainViewModel.getPopularCollections()
                },
                isInternetConnectionAvailable = isInternetConnectionAvailable,
                isSearchCalled = isSearchCalled,
                responseCode = responseCode,
                chipText = chipTitle,
                onChipSelected = mainViewModel::chipTitleSelected,
                onPhotoClicked = { photoUrl, photographer ->
                    detailsViewModel.checkPhotoInFavorites(photoUrl, photographer)
                    navigateToDetails(
                        navController = navController,
                        photoUrl = photoUrl,
                        photographer = photographer
                    )
                },
                navigateToScreen = {
                    navigateFromMainToBookmarks(navController)
                },
                getPopularPhotos = {
                    mainViewModel.homeButtonClicked()
                    mainViewModel.getCuratedPhotos()
                },
                isRefreshDataSuccess = isRefreshDataSuccess,
                swipeDown = mainViewModel::refreshData
            )
        }
        composable(
            route = "${Screen.Details.route}/{$PHOTO_URL}/{$PHOTOGRAPHER}",
            arguments = listOf(
                navArgument(PHOTO_URL) {
                    type = NavType.StringType
                    defaultValue = PHOTO_URL
                    nullable = true
                },
                navArgument(PHOTOGRAPHER) {
                    type = NavType.StringType
                    defaultValue = PHOTOGRAPHER
                    nullable = true
                }
            )
        ) { entry ->
            val photoUrl = entry.arguments?.getString(PHOTO_URL).orEmpty()
            val photographer = entry.arguments?.getString(PHOTOGRAPHER).orEmpty()
            LaunchedEffect(key1 = isPhotoInFavorites) {
                bookmarksViewModel.getBookmarksPhotos()
            }
            DetailsScreen(
                photographer = photographer,
                photoUrl = photoUrl,
                onBackClicked = {
                    navigateBack(navController)
                },
                onDownloadClicked = detailsViewModel::downloadPhoto,
                onBookmarkClicked = { url, authorName ->
                    if (isPhotoInFavorites) {
                        detailsViewModel.removePhotoFromFavorites(url, authorName)
                    } else detailsViewModel.savePhotoToFavorites(url, authorName)
                    detailsViewModel.checkPhotoInFavorites(url, authorName)
                },
                isDownloadSuccessful = isDownloadSuccessful,
                isPhotoInFavorites = isPhotoInFavorites
            )
        }
        composable(route = Screen.Bookmarks.route) {
            BookmarksScreen(
                photos = bookmarksPhotos,
                isDataLoading = isDataLoading,
                onPhotoClicked = { photoUrl, photographer ->
                    detailsViewModel.checkPhotoInFavorites(photoUrl, photographer)
                    navigateToDetailsWithPhotoUrl(
                        navController = navController,
                        photoUrl = photoUrl,
                        photographer = photographer
                    )
                },
                navigateToScreen = {
                    navigateFromBookmarksToMain(navController)
                },
                onExploreClick = {
                    navigateFromBookmarksToMain(navController)
                },
            )
        }
    }
}

private const val PHOTO_URL = "photoUrl"
private const val PHOTOGRAPHER = "authorName"

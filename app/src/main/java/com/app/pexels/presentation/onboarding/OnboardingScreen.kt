package com.app.pexels.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pexels.R
import com.app.pexels.presentation.navigation.NavigationActions
import com.app.pexels.presentation.onboarding.pager.INITIAL_PAGE
import com.app.pexels.presentation.onboarding.pager.OnBoardingPager
import com.app.pexels.presentation.onboarding.pager.OnBoardingPagerModel
import com.app.pexels.ui.theme.SetStatusBarColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun OnboardingScreen(
    navigationActions: (NavigationActions) -> Unit,
) {
    SetStatusBarColor(color = MaterialTheme.colorScheme.background, !isSystemInDarkTheme())

    val pagerState = rememberPagerState(initialPage = INITIAL_PAGE)
    val screens = ArrayList<OnBoardingPagerModel>()

    screens.add(
        OnBoardingPagerModel(
            R.raw.firstanim,
            R.string.first_screen_description,
        ),
    )

    screens.add(
        OnBoardingPagerModel(
            R.raw.secondanim,
            R.string.second_screen_description,
        ),
    )

    screens.add(
        OnBoardingPagerModel(
            R.raw.thirdanim,
            R.string.third_screen_description,
        ),
    )

    OnBoardingPager(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        listPages = screens,
        pagerState = pagerState,
        navigationActions = navigationActions,
    )
}

package com.app.pexels.presentation.onboarding.pager

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.presentation.navigation.NavigationActions
import com.app.pexels.presentation.onboarding.buttons.OnBoardingButtons
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

private const val SCREENS_NUMBER = 3
private const val PAGE_OFFSET = 1
const val INITIAL_PAGE = 0

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier,
    listPages: List<OnBoardingPagerModel>,
    pagerState: PagerState,
    navigationActions: (NavigationActions) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val isBackEnabled = pagerState.currentPage != INITIAL_PAGE

    BackHandler(onBack = {
        val currentPage = pagerState.currentPage
        if (currentPage == INITIAL_PAGE) {
            navigationActions(NavigationActions.NavigateBack)
        } else {
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentPage - PAGE_OFFSET)
            }
        }
    }, enabled = isBackEnabled)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HorizontalPager(
            count = SCREENS_NUMBER,
            state = pagerState,
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PagerImage(
                    modifier = Modifier
                        .height(300.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    listPages[page].image,
                )
                Text(
                    text = stringResource(id = listPages[page].description),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))

        PagerIndicator(
            size = listPages.size,
            currentPage = pagerState.currentPage,
        )
        Spacer(modifier = Modifier.padding(30.dp))

        OnBoardingButtons(
            modifier = modifier,
            currentPager = pagerState.currentPage,
            navigationActions = navigationActions
        )
    }
}

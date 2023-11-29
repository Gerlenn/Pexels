package com.app.pexels.presentation.onboarding.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pexels.ui.theme.White
import androidx.compose.ui.res.stringResource
import com.app.pexels.R
import com.app.pexels.presentation.navigation.NavigationActions

private const val INDEX_THIRD_PAGE = 2

@Composable
fun OnBoardingButtons(
    modifier: Modifier = Modifier,
    currentPager: Int,
    navigationActions: (NavigationActions) -> Unit,
) {
    if (currentPager == INDEX_THIRD_PAGE) {
        Button(
            modifier = modifier,
            onClick = {
                navigationActions(NavigationActions.NavigateBack)
                navigationActions(NavigationActions.NavigateMainScreen)
                navigationActions(NavigationActions.OnboardingFinished)
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        ) {
            Text(
                text = stringResource(R.string.continue_text),
                color = White,
            )
        }
    } else {
        SkipButton(
            modifier = modifier,
            navigationActions = navigationActions
        )
    }
}

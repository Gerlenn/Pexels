package com.app.pexels.presentation.onboarding.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import com.app.pexels.R
import com.app.pexels.presentation.navigation.NavigationActions

@Composable
fun SkipButton(
    modifier: Modifier = Modifier,
    navigationActions: (NavigationActions) -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = {
            navigationActions(NavigationActions.NavigateBack)
            navigationActions(NavigationActions.NavigateMainScreen)
            navigationActions(NavigationActions.OnboardingFinished)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Transparent),
    ) {
        Text(
            text = stringResource(id = R.string.skip),
            color = MaterialTheme.colorScheme.inversePrimary,
        )
    }
}

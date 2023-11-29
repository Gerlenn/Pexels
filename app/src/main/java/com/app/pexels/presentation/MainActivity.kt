package com.app.pexels.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.view.WindowCompat
import com.app.pexels.presentation.navigation.SetupNavGraph
import com.app.pexels.ui.theme.PexelsTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PexelsTheme() {
                SetupNavGraph()
            }
        }
    }
}

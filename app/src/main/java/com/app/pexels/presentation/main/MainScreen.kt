package com.app.pexels.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.app.pexels.R
import com.app.pexels.presentation.main.MainViewModel.Companion.DEFAULT_CODE
import com.app.pexels.presentation.main.bottomnav.BottomNavigationBar
import com.app.pexels.presentation.main.bottomsheet.ErrorBottomSheet
import com.app.pexels.presentation.main.chips.ChipGroup
import com.app.pexels.presentation.main.photos.Content
import com.app.pexels.presentation.main.photos.PhotosGrid
import com.app.pexels.presentation.main.photos.selectView
import com.app.pexels.presentation.main.progress.LinearProgressBar
import com.app.pexels.presentation.main.search.SearchBar
import com.app.pexels.presentation.main.stubs.StubEmptyData
import com.app.pexels.presentation.main.stubs.StubNoInternetConnection
import com.app.pexels.presentation.model.ChipUi
import com.app.pexels.presentation.model.PhotoUi
import com.app.pexels.ui.theme.SetStatusBarColor
import com.app.pexels.util.isNotNullOrEmpty
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainScreen(
    photos: LazyPagingItems<PhotoUi>,
    chips: ImmutableList<ChipUi>,
    onSearch: (String) -> Unit,
    isDataLoading: Boolean,
    refreshData: () -> Unit,
    isInternetConnectionAvailable: Boolean,
    isSearchCalled: Boolean,
    responseCode: Int,
    chipText: String,
    onChipSelected: (String) -> Unit,
    onPhotoClicked: (
        photoUrl: String,
        authorName: String,
    ) -> Unit,
    navigateToScreen: () -> Unit,
    getPopularPhotos: () -> Unit,
    isRefreshDataSuccess: Boolean,
    swipeDown: () -> Unit,
) {

    SetStatusBarColor(color = MaterialTheme.colorScheme.background, !isSystemInDarkTheme())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 54.dp,
                    start = 24.dp,
                    end = 24.dp,
                )
        ) {
            SearchBar(
                onSearch = onSearch,
                chipText = chipText,
                onChipSelected = onChipSelected,
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    top = 24.dp
                )
        ) {
            if (isInternetConnectionAvailable || photos.isNotNullOrEmpty()) {
                ChipGroup(
                    modifier = Modifier,
                    chips = chips,
                    onChipSelected = onChipSelected,
                    chipText = chipText,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (isDataLoading) {
                LinearProgressBar()
            }
        }
        Column(
            modifier = Modifier
                .padding(
                    top = 13.dp,
                    start = 24.dp,
                    end = 24.dp,
                )
                .weight(1.8f)
        ) {

            val content = selectView(
                isInternetConnectionAvailable = isInternetConnectionAvailable,
                photos = photos,
                isSearchCalled = isSearchCalled,
            )

            when (content) {
                Content.PhotoListContent -> {
                    PhotosGrid(
                        modifier = Modifier,
                        photos = photos,
                        onPhotoClicked = onPhotoClicked,
                        isRefreshDataSuccess = isRefreshDataSuccess,
                        swipeDown = swipeDown
                    )
                }

                Content.StubEmptyDataContent -> {
                    StubEmptyData(
                        modifier = Modifier,
                        onExploreClick = refreshData
                    )
                }

                Content.NetworkStubContent -> {
                    StubNoInternetConnection(
                        modifier = Modifier,
                        onTryAgainClick = refreshData
                    )
                }
            }
        }
        BottomNavigationBar(
            modifier = Modifier
                .weight(0.2f)
                .height(64.dp),
            navigateToScreen = navigateToScreen,
            screenIndex = MAIN_SCREEN_INDEX,
            getPopularPhotos = getPopularPhotos,
        )
    }

    if (!isInternetConnectionAvailable) {
        ErrorBottomSheet(R.string.no_internet_connection)
    } else if (responseCode != DEFAULT_CODE) {
        ErrorBottomSheet(responseCode = responseCode)
    }
}

private const val MAIN_SCREEN_INDEX = 0

package com.app.pexels.presentation.main.photos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.app.pexels.presentation.model.PhotoUi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PhotosGrid(
    modifier: Modifier = Modifier,
    photos: LazyPagingItems<PhotoUi>,
    onPhotoClicked: (
        photoUrl: String,
        authorName: String,
    ) -> Unit,
    isRefreshDataSuccess: Boolean,
    swipeDown: () -> Unit,
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshDataSuccess)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = swipeDown,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (photos.loadState.refresh is LoadState.Loading) {
                LoadingItem()
            } else {
                LazyVerticalStaggeredGrid(
                    modifier = modifier,
                    columns = StaggeredGridCells.Fixed(COUNT_COLUMNS),
                    horizontalArrangement = Arrangement.spacedBy(17.dp),
                    verticalItemSpacing = 15.dp,
                ) {
                    items(photos.itemCount) { photoIndex ->
                        photos[photoIndex]?.let { photo ->
                            with(photo) {
                                PhotoBox(
                                    photoUrl = original,
                                    onPhotoClicked = { onPhotoClicked(original, photographer) },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private const val COUNT_COLUMNS = 2

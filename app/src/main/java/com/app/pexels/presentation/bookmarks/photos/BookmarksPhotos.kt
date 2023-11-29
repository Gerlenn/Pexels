package com.app.pexels.presentation.bookmarks.photos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.pexels.presentation.model.PhotoDataUi
import com.app.pexels.presentation.shimmer.ShimmerEffectForPhotos
import kotlinx.collections.immutable.ImmutableList

@Composable
fun BookmarksPhotos(
    modifier: Modifier = Modifier,
    photos: ImmutableList<PhotoDataUi>,
    isDataLoading: Boolean,
    onPhotoClicked: (
        photoUrl: String,
        photographer: String,
    ) -> Unit,
) {

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(COUNT_COLUMNS),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalItemSpacing = 15.dp,
    ) {
        items(photos) { photo ->
            ShimmerEffectForPhotos(
                isDataLoading = isDataLoading,
                contentAfterLoading = {
                    with(photo) {
                        BookmarksPhotoBox(
                            photoUrl = photo.photoUrl,
                            photographer = photo.photographer,
                            onPhotoClicked = {
                                onPhotoClicked(
                                    photoUrl,
                                    photographer
                                )
                            },
                        )
                    }
                },
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

private const val COUNT_COLUMNS = 2

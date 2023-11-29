package com.app.pexels.presentation.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.presentation.bookmarks.photos.BookmarksPhotos
import com.app.pexels.presentation.bookmarks.stub.StubEmptyBookmarks
import com.app.pexels.presentation.main.bottomnav.BottomNavigationBar
import com.app.pexels.presentation.model.PhotoDataUi
import com.app.pexels.ui.theme.CustomMulishW700
import com.app.pexels.ui.theme.SetStatusBarColor
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BookmarksScreen(
    photos: ImmutableList<PhotoDataUi>,
    isDataLoading: Boolean,
    onPhotoClicked: (
        photoUrl: String,
        photographer: String,
    ) -> Unit,
    navigateToScreen: () -> Unit,
    onExploreClick: () -> Unit,
) {

    val context = LocalContext.current

    SetStatusBarColor(color = MaterialTheme.colorScheme.background, !isSystemInDarkTheme())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 54.dp,
                    start = 24.dp,
                    end = 24.dp,
                ),
        ) {
            Column(
                modifier = Modifier.height(40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = context.getString(R.string.bookmarks),
                        fontFamily = CustomMulishW700,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(
                    top = 13.dp,
                    start = 24.dp,
                    end = 24.dp,
                )
                .weight(2f)
        ) {
            if (photos.isNotEmpty()) {
                BookmarksPhotos(
                    modifier = Modifier,
                    photos = photos,
                    isDataLoading = isDataLoading,
                    onPhotoClicked = onPhotoClicked
                )
            } else {
                StubEmptyBookmarks(
                    modifier = Modifier,
                    onExploreClick = onExploreClick
                )
            }
        }
        BottomNavigationBar(
            modifier = Modifier
                .weight(0.2f)
                .height(64.dp),
            navigateToScreen = navigateToScreen,
            screenIndex = BOOKMARKS_SCREEN_INDEX,
            getPopularPhotos = onExploreClick,
        )
    }
}

private const val BOOKMARKS_SCREEN_INDEX = 1

@Preview()
@Composable
fun BookmarksScreenPreview() {
    BookmarksScreen(
        photos = persistentListOf(),
        isDataLoading = false,
        onPhotoClicked = { _, _ -> },
        navigateToScreen = {},
        onExploreClick = {}
    )
}

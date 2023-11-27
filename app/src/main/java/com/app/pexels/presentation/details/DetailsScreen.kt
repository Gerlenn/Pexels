package com.app.pexels.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pexels.R
import com.app.pexels.presentation.details.bottom.BottomButtons
import com.app.pexels.presentation.details.image.MainImage
import com.app.pexels.presentation.details.top.TopBar
import com.app.pexels.ui.theme.SetStatusBarColor
import com.app.pexels.ui.theme.White
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DetailsScreen(
    photoUrl: String,
    photographer: String,
    onBackClicked: () -> Unit,
    onDownloadClicked: (String) -> Unit,
    onBookmarkClicked: (String, String) -> Unit,
    isDownloadSuccessful: Boolean,
    isPhotoInFavorites: Boolean,
) {

    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val isShowSnackBar = remember { mutableStateOf(false) }
    val context = LocalContext.current

    SetStatusBarColor(color = MaterialTheme.colorScheme.background, !isSystemInDarkTheme())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.inversePrimary,
                    actionColor = MaterialTheme.colorScheme.inversePrimary,
                    snackbarData = data
                )
            }
        },
        containerColor = White,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding()
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
                TopBar(
                    modifier = Modifier,
                    onBackClicked = onBackClicked,
                    photographer = photographer,
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 29.dp,
                        bottom = 24.dp,
                    )
                    .weight(6f),
            ) {
                MainImage(
                    modifier = Modifier,
                    photoUrl = photoUrl,
                )
            }
            BottomButtons(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                    ),
                onDownloadClicked = {
                    onDownloadClicked(photoUrl)
                    isShowSnackBar.value = true
                },
                onBookmarkClicked = { onBookmarkClicked(photoUrl, photographer) },
                isPhotoInFavorites = isPhotoInFavorites,
            )
        }
    }
    if (isShowSnackBar.value) {
        coroutineScope.launch {
            val downloadMessage =
                if (isDownloadSuccessful) R.string.downloading_successful else R.string.downloading_error
            snackBarHostState.showSnackbar(
                message = context.getString(downloadMessage),
                actionLabel = context.getString(R.string.close_snack_bar),
                duration = SnackbarDuration.Short,
            )
        }
        isShowSnackBar.value = false
    }
}

@Preview()
@Composable
fun DetailsPreview() {
    DetailsScreen(
        photoUrl = "",
        photographer = "",
        onBackClicked = {},
        onDownloadClicked = {},
        onBookmarkClicked = { _, _ -> },
        isDownloadSuccessful = false,
        isPhotoInFavorites = false,
    )
}

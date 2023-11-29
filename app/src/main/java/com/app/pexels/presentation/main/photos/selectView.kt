package com.app.pexels.presentation.main.photos

import androidx.paging.compose.LazyPagingItems
import com.app.pexels.presentation.model.PhotoUi
import com.app.pexels.util.isNotNullOrEmpty

fun selectView(
    isInternetConnectionAvailable: Boolean,
    photos: LazyPagingItems<PhotoUi>,
    isSearchCalled: Boolean,
): Content = when {

    isInternetConnectionAvailable && photos.isNotNullOrEmpty() -> Content.PhotoListContent
    isInternetConnectionAvailable && !isSearchCalled -> Content.StubEmptyDataContent
    isInternetConnectionAvailable -> Content.NetworkStubContent
    !isInternetConnectionAvailable && photos.isNotNullOrEmpty() && isSearchCalled -> Content.NetworkStubContent
    photos.isNotNullOrEmpty() -> Content.PhotoListContent
    else -> Content.NetworkStubContent
}

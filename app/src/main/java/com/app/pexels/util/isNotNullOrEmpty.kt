package com.app.pexels.util

import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyPagingItems<T>?.isNotNullOrEmpty(): Boolean = (this?.itemCount ?: 0) > 0
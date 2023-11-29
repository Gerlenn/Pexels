package com.app.pexels.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.presentation.main.mapper.mapPhotoDataToUi
import com.app.pexels.presentation.model.PhotoDataUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val pexelsRepository: PexelsRepository,
) : ViewModel() {

    private val _bookmarksPhotos = MutableStateFlow<ImmutableList<PhotoDataUi>>(persistentListOf())
    val bookmarksPhotos: StateFlow<ImmutableList<PhotoDataUi>> = _bookmarksPhotos.asStateFlow()

    init {
        getBookmarksPhotos()
    }

    fun getBookmarksPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarksPhotos = pexelsRepository.getBookmarksPhotos()
            _bookmarksPhotos.emit(bookmarksPhotos.toImmutableList().map { it.mapPhotoDataToUi() }.toImmutableList())
        }
    }
}

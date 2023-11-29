package com.app.pexels.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.util.download.PhotoDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val pexelsRepository: PexelsRepository,
    private val photoDownloader: PhotoDownloader,
) : ViewModel() {

    private val _isDownloadSuccessful = MutableStateFlow(true)
    val isDownloadSuccessful: StateFlow<Boolean> = _isDownloadSuccessful.asStateFlow()

    private val _isPhotoInFavorites = MutableStateFlow(false)
    val isPhotoInFavorites: StateFlow<Boolean> = _isPhotoInFavorites.asStateFlow()

    fun downloadPhoto(photoUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = photoDownloader.downloadPhoto(photoUrl)
            if (result.isSuccess) {
                _isDownloadSuccessful.tryEmit(true)
            } else {
                _isDownloadSuccessful.tryEmit(false)
            }
        }
    }

    fun savePhotoToFavorites(imageUrl: String, photographer: String) {
        pexelsRepository.savePhotoToFavorites(imageUrl, photographer)
    }

    fun removePhotoFromFavorites(imageUrl: String, photographer: String) {
        pexelsRepository.removePhotoFromFavorites(imageUrl, photographer)
    }

    fun checkPhotoInFavorites(imageUrl: String, photographer: String) {
        _isPhotoInFavorites.tryEmit(pexelsRepository.checkPhotoInFavorites(imageUrl, photographer))
    }
}

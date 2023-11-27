package com.app.pexels.data

import com.app.pexels.data.mappers.mapChipToDomain
import com.app.pexels.data.mappers.mapPhotoDataToDomain
import com.app.pexels.data.mappers.mapPhotoToDomain
import com.app.pexels.data.model.DataAndResponseCode
import com.app.pexels.data.service.PexelsService
import com.app.pexels.data.sharedpreferences.OnboardingSharedPreferencesDelegate
import com.app.pexels.data.sharedpreferences.PhotosSharedPreferencesDelegate
import com.app.pexels.data.utils.handleApiResponse
import com.app.pexels.domain.model.ChipDomain
import com.app.pexels.domain.model.PhotoDataDomain
import com.app.pexels.domain.model.PhotoDomain
import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.util.orEmptyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PexelsRepositoryImpl @Inject constructor(
    private val pexelsService: PexelsService,
    onboardingSharedPreferencesDelegate: OnboardingSharedPreferencesDelegate,
    private val photosSharedPreferencesDelegate: PhotosSharedPreferencesDelegate,
) : PexelsRepository {

    private var onboardingSharedPreferencesDelegate by onboardingSharedPreferencesDelegate

    override suspend fun getCuratedPhotos(page: Int, perPage: Int): DataAndResponseCode<List<PhotoDomain>> =
        withContext(Dispatchers.IO) {
            handleApiResponse(pexelsService.getCuratedPhotos(page, perPage)) { response ->
                response?.photos?.map { it.mapPhotoToDomain() }.orEmptyList()
            }
        }

    override suspend fun getPopularCollections(perPage: Int): DataAndResponseCode<List<ChipDomain>> =
        withContext(Dispatchers.IO) {
            handleApiResponse(pexelsService.getPopularCollections(perPage)) { response ->
                response?.listChipTitle?.map { it.mapChipToDomain() }.orEmptyList()
            }
        }

    override suspend fun getSearchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): DataAndResponseCode<List<PhotoDomain>> =
        withContext(Dispatchers.IO) {
            handleApiResponse(pexelsService.getSearchPhotos(query, page, perPage)) { response ->
                response?.photos?.map { it.mapPhotoToDomain() }.orEmptyList()
            }
        }

    override fun setOnboardingState(state: Boolean) {
        onboardingSharedPreferencesDelegate = state
    }

    override fun getOnBoardingState() = onboardingSharedPreferencesDelegate

    override fun savePhotoToFavorites(imageUrl: String, photographer: String) {
        photosSharedPreferencesDelegate.savePhotoToFavorites(imageUrl, photographer)
    }

    override fun removePhotoFromFavorites(imageUrl: String, photographer: String) {
        photosSharedPreferencesDelegate.removePhotoFromFavorites(imageUrl, photographer)
    }

    override fun checkPhotoInFavorites(imageUrl: String, photographer: String) =
        photosSharedPreferencesDelegate.checkPhotoInFavorites(imageUrl, photographer)

    override fun getBookmarksPhotos(): List<PhotoDataDomain> =
        photosSharedPreferencesDelegate.getBookmarksPhotos().map { it.mapPhotoDataToDomain() }.orEmptyList()
}

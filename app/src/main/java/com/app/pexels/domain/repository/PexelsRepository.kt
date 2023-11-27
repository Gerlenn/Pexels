package com.app.pexels.domain.repository

import com.app.pexels.data.model.DataAndResponseCode
import com.app.pexels.domain.model.ChipDomain
import com.app.pexels.domain.model.PhotoDataDomain
import com.app.pexels.domain.model.PhotoDomain

interface PexelsRepository {

    fun setOnboardingState(state: Boolean)

    fun getOnBoardingState(): Boolean

    suspend fun getCuratedPhotos(page: Int, perPage: Int): DataAndResponseCode<List<PhotoDomain>>

    suspend fun getPopularCollections(perPage: Int): DataAndResponseCode<List<ChipDomain>>

    suspend fun getSearchPhotos(query: String, page: Int, perPage: Int): DataAndResponseCode<List<PhotoDomain>>

    fun savePhotoToFavorites(imageUrl: String, photographer: String)

    fun removePhotoFromFavorites(imageUrl: String, photographer: String)

    fun checkPhotoInFavorites(imageUrl: String, photographer: String): Boolean

    fun getBookmarksPhotos(): List<PhotoDataDomain>
}
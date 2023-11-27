package com.app.pexels.data.service

import com.app.pexels.data.model.ChipTitleResponseDto
import com.app.pexels.data.model.PixelsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsService {

    @GET(CURATED_RESPONSE)
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PixelsResponseDto>

    @GET(CHIP_TITLE_RESPONSE)
    suspend fun getPopularCollections(
        @Query("per_page") perPage: Int,
    ): Response<ChipTitleResponseDto>

    @GET(SEARCH_RESPONSE)
    suspend fun getSearchPhotos(
        @Query("query") category: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PixelsResponseDto>

    companion object {

        private const val CURATED_RESPONSE = "curated"
        private const val CHIP_TITLE_RESPONSE = "collections/featured"
        private const val SEARCH_RESPONSE = "search"
    }
}
package com.app.pexels.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.pexels.domain.model.PhotoDomain
import com.app.pexels.domain.repository.PexelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoPagingSource(
    private val pexelsRepository: PexelsRepository,
    private val searchQueryText: String?,
) : PagingSource<Int, PhotoDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> {
        val page = params.key ?: ONE_PAGE
        val pageSize = params.loadSize
        val response = getResponseFromRepository(page, pageSize)
        val nextKey = response.takeIf { it.isNotEmpty() }?.let { page + ONE_PAGE }
        val prevKey = page.takeIf { it > ONE_PAGE }?.minus(ONE_PAGE)
        return LoadResult.Page(response, prevKey, nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(ONE_PAGE) ?: anchorPage?.nextKey?.minus(ONE_PAGE)
        }

    private suspend fun getResponseFromRepository(page: Int, pageSize: Int): List<PhotoDomain> =
        withContext(Dispatchers.IO) {
            val response = if (searchQueryText.isNullOrEmpty()) {
                pexelsRepository.getCuratedPhotos(page = page, perPage = pageSize)
            } else {
                pexelsRepository.getSearchPhotos(query = searchQueryText, page = page, perPage = pageSize)
            }
            response.dataFromServer
        }

    companion object {

        private const val ONE_PAGE = 1
    }
}

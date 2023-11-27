package com.app.pexels.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.app.pexels.data.paging.PhotoPagingSource
import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.presentation.main.mapper.mapToUi
import com.app.pexels.presentation.model.ChipUi
import com.app.pexels.presentation.model.ErrorResponseCode
import com.app.pexels.presentation.model.PhotoUi
import com.app.pexels.util.internet.InternetConnectionStatus
import com.app.pexels.util.internet.NetworkConnectivityObserver
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val pexelsRepository: PexelsRepository,
    private val connectivityObserver: NetworkConnectivityObserver,
) : ViewModel() {

    private val _popularCollections = MutableStateFlow<ImmutableList<ChipUi>>(persistentListOf())
    val popularCollections: StateFlow<ImmutableList<ChipUi>> = _popularCollections.asStateFlow()

    private val _isDataLoading = MutableStateFlow(false)
    val isDataLoading: StateFlow<Boolean> = _isDataLoading.asStateFlow()

    private val _isInternetConnectionAvailable = MutableStateFlow(false)
    val isInternetConnectionAvailable: StateFlow<Boolean> = _isInternetConnectionAvailable.asStateFlow()

    private val _isSearchCalled = MutableStateFlow(false)
    val isSearchCalled: StateFlow<Boolean> = _isSearchCalled.asStateFlow()

    private val _responseCodes = MutableStateFlow(ErrorResponseCode.DEFAULT_RESPONSE_CODE.messageResId)
    val responseCodes: StateFlow<Int> = _responseCodes.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    private var _chipTitle = MutableStateFlow(EMPTY_QUERY)
    var chipTitle: StateFlow<String> = _chipTitle.asStateFlow()

    private val searchJobState = MutableStateFlow<Job?>(null)

    private val queryText = MutableStateFlow(EMPTY_QUERY)

    private val _isRefreshDataSuccess = MutableStateFlow(false)
    val isRefreshDataSuccess: StateFlow<Boolean> = _isRefreshDataSuccess.asStateFlow()

    init {
        getPopularCollections()
        observeConnectivity()
    }

    private suspend fun handleResponseCode(code: Int) {
        when (code) {
            NOT_FOUND_CODE -> _responseCodes.emit(ErrorResponseCode.NOT_FOUND_CODE.messageResId)
            SERVER_ERROR_CODE -> _responseCodes.emit(ErrorResponseCode.SERVER_ERROR_CODE.messageResId)
            PAGING_DATA_LOADING_ERROR -> _responseCodes.emit(ErrorResponseCode.PAGING_DATA_LOADING_ERROR.messageResId)
            else -> _responseCodes.emit(ErrorResponseCode.DEFAULT_RESPONSE_CODE.messageResId)
        }
    }

    var pagingData: Flow<PagingData<PhotoUi>> = queryText.flatMapLatest { queryText ->
        Pager(
            config = PagingConfig(pageSize = PHOTOS_PER_PAGE),
            pagingSourceFactory = { PhotoPagingSource(pexelsRepository, queryText) }
        ).flow.map { pagingData ->
            pagingData.map { photoDomain ->
                photoDomain.mapToUi()
            }
        }.catch {
            handleResponseCode(PAGING_DATA_LOADING_ERROR)
        }
    }

    fun getCuratedPhotos() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _isSearchCalled.emit(false)
            val result = pexelsRepository.getCuratedPhotos(page = DEFAULT_PAGE, perPage = PHOTOS_PER_PAGE)
            toggleDataLoadingState {
                if (result.code == SUCCESSFUL_CODE) {
                    queryText.emit(EMPTY_QUERY)
                } else handleResponseCode(result.code)
            }
        }
    }

    fun getPopularCollections() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            toggleDataLoadingState {
                val result = pexelsRepository.getPopularCollections(perPage = CHIP_TITLE_PER_PAGE)
                if (result.code == SUCCESSFUL_CODE) {
                    _popularCollections.emit(result.dataFromServer.map { it.mapToUi() }.toImmutableList())
                } else handleResponseCode(result.code)
            }
        }
    }

    fun getSearchPhotos(query: String) {
        searchJobState.value?.cancel()
        if (query.length >= MINIMAL_LENGTH) {
            searchJobState.tryEmit(viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                delay(REQUEST_DELAY)
                _isSearchCalled.emit(true)
                toggleDataLoadingState {
                    val result = pexelsRepository.getSearchPhotos(query, page = DEFAULT_PAGE, perPage = PHOTOS_PER_PAGE)
                    if (result.code == SUCCESSFUL_CODE) {
                        queryText.emit(query)
                    } else handleResponseCode(result.code)
                }
                _isSearchCalled.emit(false)
            })
        }
    }

    private fun observeConnectivity() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            connectivityObserver.observe().map { status -> status == InternetConnectionStatus.Available }
                .collect { isConnected ->
                    _isInternetConnectionAvailable.emit(isConnected)
                    _responseCodes.emit(DEFAULT_CODE)
                }
        }
    }

    private suspend fun toggleDataLoadingState(action: suspend () -> Unit) {
        if (isInternetConnectionAvailable.value) {
            _isDataLoading.emit(true)
        }
        action()
        _isDataLoading.emit(false)
    }

    fun chipTitleSelected(title: String) {
        _chipTitle.tryEmit(title)
    }

    fun homeButtonClicked() {
        _chipTitle.tryEmit(EMPTY_QUERY)
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshDataSuccess.emit(true)
            createNewPager()
            _isRefreshDataSuccess.emit(false)
        }
    }

    private fun createNewPager() {
        val newPager = Pager(
            config = PagingConfig(pageSize = PHOTOS_PER_PAGE),
            pagingSourceFactory = { PhotoPagingSource(pexelsRepository, queryText.value) }
        )
        pagingData = newPager.flow
            .map { pagingData ->
                pagingData.map { photoDomain ->
                    photoDomain.mapToUi()
                }
            }
            .cachedIn(viewModelScope)
            .catch {
                handleResponseCode(PAGING_DATA_LOADING_ERROR)
            }
    }


    companion object {

        private const val CHIP_TITLE_PER_PAGE = 7
        private const val PHOTOS_PER_PAGE = 30
        private const val DEFAULT_PAGE = 1
        private const val EMPTY_QUERY = ""
        const val DEFAULT_CODE = 0
        const val SUCCESSFUL_CODE = 200
        const val NOT_FOUND_CODE = 404
        const val SERVER_ERROR_CODE = 500
        const val PAGING_DATA_LOADING_ERROR = 1
        private const val REQUEST_DELAY = 2000L
        private const val MINIMAL_LENGTH = 2
    }
}

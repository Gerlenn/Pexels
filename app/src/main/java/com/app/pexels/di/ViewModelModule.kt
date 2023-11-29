package com.app.pexels.di

import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.presentation.bookmarks.BookmarksViewModel
import com.app.pexels.presentation.details.DetailsViewModel
import com.app.pexels.presentation.factory.ViewModelFactory
import com.app.pexels.presentation.main.MainViewModel
import com.app.pexels.presentation.onboarding.OnBoardingViewModel
import com.app.pexels.util.download.PhotoDownloader
import com.app.pexels.util.internet.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ViewModelModule {

    @Provides
    @Named(MAIN_FACTORY)
    fun provideMainViewModelFactory(mainViewModel: MainViewModel): ViewModelFactory =
        ViewModelFactory(mainViewModel)

    @Provides
    @Named(ONBOARDING_FACTORY)
    fun provideOnBoardingViewModelFactory(onBoardingViewModel: OnBoardingViewModel): ViewModelFactory =
        ViewModelFactory(onBoardingViewModel)

    @Provides
    @Named(DETAILS_FACTORY)
    fun provideDetailsViewModelFactory(detailsViewModel: DetailsViewModel): ViewModelFactory =
        ViewModelFactory(detailsViewModel)

    @Provides
    @Named(BOOKMARKS_FACTORY)
    fun provideBookmarksViewModelFactory(bookmarksViewModel: BookmarksViewModel): ViewModelFactory =
        ViewModelFactory(bookmarksViewModel)

    @Provides
    fun provideMainViewModel(
        pexelsRepository: PexelsRepository,
        connectivityObserver: NetworkConnectivityObserver,
    ): MainViewModel = MainViewModel(pexelsRepository, connectivityObserver)

    @Provides
    fun provideOnBoardingViewModel(pexelsRepository: PexelsRepository): OnBoardingViewModel =
        OnBoardingViewModel(pexelsRepository)

    @Provides
    fun provideDetailsViewModel(
        pexelsRepository: PexelsRepository,
        photoDownloader: PhotoDownloader,
    ): DetailsViewModel = DetailsViewModel(pexelsRepository, photoDownloader)

    @Provides
    fun provideBookmarksViewModel(pexelsRepository: PexelsRepository): BookmarksViewModel =
        BookmarksViewModel(pexelsRepository)

    companion object {

        const val ONBOARDING_FACTORY = "OnBoardingViewModelFactory"
        const val MAIN_FACTORY = "MainViewModelFactory"
        const val DETAILS_FACTORY = "DetailsViewModelFactory"
        const val BOOKMARKS_FACTORY = "BookmarksViewModelFactory"
    }
}

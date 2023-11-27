package com.app.pexels.di

import com.app.pexels.di.ViewModelModule.Companion.BOOKMARKS_FACTORY
import com.app.pexels.di.ViewModelModule.Companion.DETAILS_FACTORY
import com.app.pexels.di.ViewModelModule.Companion.MAIN_FACTORY
import com.app.pexels.di.ViewModelModule.Companion.ONBOARDING_FACTORY
import com.app.pexels.presentation.BaseActivity
import com.app.pexels.presentation.factory.ViewModelFactory
import com.app.pexels.util.workmanager.PexelsWorkerFactory
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class,
        WorkManagerModule::class,
    ],
)
interface AppComponent {

    @Named(ONBOARDING_FACTORY)
    fun onBoardingViewModelFactory(): ViewModelFactory

    @Named(MAIN_FACTORY)
    fun mainViewModelFactory(): ViewModelFactory

    @Named(DETAILS_FACTORY)
    fun detailsViewModelFactory(): ViewModelFactory

    @Named(BOOKMARKS_FACTORY)
    fun bookmarksViewModelFactory(): ViewModelFactory

    fun inject(baseActivity: BaseActivity)

    fun pexelsWorkerFactory(): PexelsWorkerFactory
}

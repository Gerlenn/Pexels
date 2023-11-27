package com.app.pexels.di

import com.app.pexels.data.PexelsRepositoryImpl
import com.app.pexels.domain.repository.PexelsRepository
import com.app.pexels.util.workmanager.WorkExecutor
import com.app.pexels.util.workmanager.WorkExecutorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindPixelsRepository(
        pixelsRepositoryImpl: PexelsRepositoryImpl,
    ): PexelsRepository

    @Singleton
    @Binds
    abstract fun bindWorkExecutor(
        workExecutorImpl: WorkExecutorImpl,
    ): WorkExecutor
}

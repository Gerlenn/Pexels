package com.app.pexels.di

import android.content.Context
import androidx.work.WorkManager
import com.app.pexels.util.workmanager.BaseWorkExecutor
import com.app.pexels.util.workmanager.WorkExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorkManagerModule {

    @Singleton
    @Provides
    fun provideWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideBaseWorkExecutor(workExecutor: WorkExecutor): BaseWorkExecutor = BaseWorkExecutor(workExecutor)
}

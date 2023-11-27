package com.app.pexels.util.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class PexelsWorkerFactory @Inject constructor(
    private val clearCacheWorkerFactory: ClearCacheWorkerFactory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? =
        when (workerClassName) {
            ClearCacheWorker::class.java.name -> clearCacheWorkerFactory.create(appContext, workerParameters)
            else -> null
        }
}

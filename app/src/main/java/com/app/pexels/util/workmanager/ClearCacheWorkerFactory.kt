package com.app.pexels.util.workmanager

import android.content.Context
import androidx.work.WorkerParameters
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ClearCacheWorkerFactory {

    fun create(appContext: Context, params: WorkerParameters): ClearCacheWorker
}
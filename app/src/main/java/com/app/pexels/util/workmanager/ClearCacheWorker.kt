package com.app.pexels.util.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.app.pexels.util.workmanager.BaseWorkExecutor.Companion.DATA_KEY_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.Cache

class ClearCacheWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val cache: Cache,
) : Worker(context, params) {

    override fun doWork(): Result =
        try {
            inputData.getString(DATA_KEY_NAME)
            cache.evictAll()
            Result.success()
        } catch (_: Exception) {
            Result.failure()
        }
}

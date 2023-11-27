package com.app.pexels.util.workmanager

import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BaseWorkExecutor @Inject constructor(private val workExecutor: WorkExecutor) {

    fun clearCacheJob() {
        val inputData = Data
            .Builder()
            .putString(DATA_KEY_NAME, formatTime(System.currentTimeMillis()))
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ClearCacheWorker>()
            .addTag(CLEAR_CACHE_REQUEST_TAG)
            .setInputData(inputData)
            .setInitialDelay(CLEAR_CACHE_REQUEST_DELAY, TimeUnit.HOURS)
            .build()
        workExecutor.executeOneTimeWork(workRequest)
    }

    fun cancelWorkRequest(workRequestTag: String) {
        workExecutor.cancelWorkRequest(workRequestTag)
    }

    fun isActiveWorkWithTag(context: Context, workRequestTag: String): Boolean =
        workExecutor.isActiveWorkWithTag(context, workRequestTag)

    private fun formatTime(timeInMillis: Long, locale: Locale = Locale.getDefault()): String =
        SimpleDateFormat.getDateTimeInstance(
            SimpleDateFormat.RELATIVE_SHORT, SimpleDateFormat.RELATIVE_SHORT, locale
        ).format(Date(timeInMillis))

    companion object {

        private const val CLEAR_CACHE_REQUEST_DELAY = 1L
        const val CLEAR_CACHE_REQUEST_TAG = "clearCache"
        const val DATA_KEY_NAME = "currentTime"
    }
}

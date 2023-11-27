package com.app.pexels.util.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import javax.inject.Inject

class WorkExecutorImpl @Inject constructor(
    private val workManager: WorkManager,
) : WorkExecutor {

    override fun executeOneTimeWork(request: OneTimeWorkRequest) {
        workManager.enqueue(request)
    }

    override fun cancelWorkRequest(workRequestTag: String) {
        workManager.cancelAllWorkByTag(workRequestTag)
    }

    override fun isActiveWorkWithTag(context: Context, workRequestTag: String): Boolean =
        workManager.getWorkInfosByTag(workRequestTag).get().any { it.state == WorkInfo.State.RUNNING }
}

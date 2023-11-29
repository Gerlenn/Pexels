package com.app.pexels.util.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequest

interface WorkExecutor {

    fun executeOneTimeWork(request: OneTimeWorkRequest)

    fun cancelWorkRequest(workRequestTag: String)

    fun isActiveWorkWithTag(context: Context, workRequestTag: String): Boolean
}
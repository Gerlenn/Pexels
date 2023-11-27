package com.app.pexels.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.app.pexels.App
import com.app.pexels.util.workmanager.BaseWorkExecutor
import com.app.pexels.util.workmanager.BaseWorkExecutor.Companion.CLEAR_CACHE_REQUEST_TAG
import javax.inject.Inject

abstract class BaseActivity : ComponentActivity() {

    @Inject
    lateinit var baseWorkExecutor: BaseWorkExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).provideAppComponent().inject(this)
    }

    override fun onResume() {
        val isActiveWork = baseWorkExecutor.isActiveWorkWithTag(this, CLEAR_CACHE_REQUEST_TAG)
        if (!isActiveWork) {
            baseWorkExecutor.cancelWorkRequest(CLEAR_CACHE_REQUEST_TAG)
        }
        super.onResume()
    }

    override fun onStop() {
        baseWorkExecutor.clearCacheJob()
        super.onStop()
    }
}

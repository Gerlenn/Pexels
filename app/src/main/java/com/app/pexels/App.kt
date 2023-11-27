package com.app.pexels

import android.app.Application
import androidx.work.Configuration
import com.app.pexels.di.AppComponent
import com.app.pexels.di.AppModule
import com.app.pexels.di.DaggerAppComponent
import com.app.pexels.di.DataModule

class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(provideAppComponent().pexelsWorkerFactory())
            .build()

    fun provideAppComponent(): AppComponent {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(this))
            .build()
        return appComponent
    }

    companion object {

        lateinit var appComponent: AppComponent
    }
}

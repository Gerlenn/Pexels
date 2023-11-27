package com.app.pexels.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.pexels.data.service.PexelsService
import com.app.pexels.util.download.PhotoDownloader
import com.app.pexels.util.internet.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideCustomHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor) = OkHttpClient.Builder()
        .cache(provideCache())
        .addInterceptor(headerInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PexelsService = retrofit.create(PexelsService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences =
        EncryptedSharedPreferences.create(
            ENCRYPTED_PREF_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Singleton
    @Provides
    fun provideNetworkConnectivityObserver(): NetworkConnectivityObserver =
        NetworkConnectivityObserver(context)

    @Singleton
    @Provides
    fun providePhotoDownloader(): PhotoDownloader =
        PhotoDownloader(context)

    @Singleton
    @Provides
    fun provideCache(): Cache = Cache(context.cacheDir, CACHE_SIZE.toLong())

    companion object {

        private const val BASE_URL = "https://api.pexels.com/v1/"
        private const val CACHE_SIZE = 10 * 1024 * 1024
        private const val ENCRYPTED_PREF_NAME = "encryptedPrefName"
    }
}

package com.app.pexels.util.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.app.pexels.R
import javax.inject.Inject

class PhotoDownloader @Inject constructor(context: Context) : Downloader {

    private val request = context.getSystemService(DownloadManager::class.java)
    private val mimeType = context.getString(R.string.image_jpeg)
    private val destinationSubPath = context.getString(R.string.photo_jpg)

    override fun downloadPhoto(photoUrl: String): Result<IsDownloadSuccess> {
        return try {
            request.enqueue(
                DownloadManager.Request(photoUrl.toUri())
                    .setMimeType(mimeType)
                    .setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI
                    )
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationSubPath)
            )
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

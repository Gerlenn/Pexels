package com.app.pexels.util.download

typealias IsDownloadSuccess = Boolean

interface Downloader {

    fun downloadPhoto(photoUrl: String): Result<IsDownloadSuccess>
}
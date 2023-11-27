package com.app.pexels.data.model

import com.google.gson.annotations.SerializedName

data class PhotoResponseDto(
    @SerializedName("id") val photoId: Int?,
    @SerializedName("photographer") val photographerName: String?,
    @SerializedName("src") val sizePhotos: SizePhotoUrlResponseDto,
)
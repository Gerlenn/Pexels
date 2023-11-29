package com.app.pexels.data.model

import com.google.gson.annotations.SerializedName

data class SizePhotoUrlResponseDto(
    @SerializedName("large2x") val originalSizePhotoUrl: String?,
)
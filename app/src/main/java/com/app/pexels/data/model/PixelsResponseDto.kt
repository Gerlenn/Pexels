package com.app.pexels.data.model

import com.google.gson.annotations.SerializedName

data class PixelsResponseDto(
    @SerializedName("photos") val photos: List<PhotoResponseDto>,
)
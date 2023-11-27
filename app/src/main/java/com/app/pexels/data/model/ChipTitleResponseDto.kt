package com.app.pexels.data.model

import com.google.gson.annotations.SerializedName

data class ChipTitleResponseDto(
    @SerializedName("collections") val listChipTitle: List<ChipResponseDto>,
)
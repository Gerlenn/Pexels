package com.app.pexels.data.mappers

import com.app.pexels.data.model.PhotoResponseDto
import com.app.pexels.domain.model.PhotoDomain
import com.app.pexels.util.orZero

fun PhotoResponseDto.mapPhotoToDomain() = PhotoDomain(
    id = photoId.orZero(),
    original = sizePhotos.originalSizePhotoUrl.orEmpty(),
    photographer = photographerName.orEmpty()
)

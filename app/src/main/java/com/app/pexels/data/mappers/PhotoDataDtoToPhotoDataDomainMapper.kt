package com.app.pexels.data.mappers

import com.app.pexels.data.model.PhotoDataDto
import com.app.pexels.domain.model.PhotoDataDomain

fun PhotoDataDto.mapPhotoDataToDomain(): PhotoDataDomain =
    PhotoDataDomain(
        photoUrl = photoUrl.orEmpty(),
        photographer = photographer.orEmpty()
    )


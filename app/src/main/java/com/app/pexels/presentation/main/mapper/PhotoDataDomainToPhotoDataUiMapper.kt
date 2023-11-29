package com.app.pexels.presentation.main.mapper

import com.app.pexels.domain.model.PhotoDataDomain
import com.app.pexels.presentation.model.PhotoDataUi

fun PhotoDataDomain.mapPhotoDataToUi(): PhotoDataUi =
    PhotoDataUi(
        photoUrl = photoUrl,
        photographer = photographer
    )


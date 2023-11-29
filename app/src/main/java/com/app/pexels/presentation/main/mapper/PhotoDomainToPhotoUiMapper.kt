package com.app.pexels.presentation.main.mapper

import com.app.pexels.domain.model.PhotoDomain
import com.app.pexels.presentation.model.PhotoUi

fun PhotoDomain.mapToUi() = PhotoUi(
    id = id,
    original = original,
    photographer = photographer
)

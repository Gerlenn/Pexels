package com.app.pexels.presentation.main.mapper

import com.app.pexels.domain.model.ChipDomain
import com.app.pexels.presentation.model.ChipUi

fun ChipDomain.mapToUi() = ChipUi(title = title)

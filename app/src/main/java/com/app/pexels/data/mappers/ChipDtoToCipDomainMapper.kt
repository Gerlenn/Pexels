package com.app.pexels.data.mappers

import com.app.pexels.data.model.ChipResponseDto
import com.app.pexels.domain.model.ChipDomain

fun ChipResponseDto.mapChipToDomain() = ChipDomain(title = title.orEmpty())

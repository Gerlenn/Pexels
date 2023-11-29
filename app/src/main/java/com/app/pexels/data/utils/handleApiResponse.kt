package com.app.pexels.data.utils

import com.app.pexels.data.model.DataAndResponseCode

fun <T, R> handleApiResponse(
    response: retrofit2.Response<T>,
    mapper: (T?) -> List<R>,
): DataAndResponseCode<List<R>> =

    if (response.isSuccessful) {
        DataAndResponseCode(mapper(response.body()), response.code())
    } else {
        DataAndResponseCode(emptyList(), response.code())
    }

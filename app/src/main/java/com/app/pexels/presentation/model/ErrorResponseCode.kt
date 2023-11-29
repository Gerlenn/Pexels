package com.app.pexels.presentation.model

import com.app.pexels.R

enum class ErrorResponseCode(val messageResId: Int) {

    NOT_FOUND_CODE(R.string.not_found_error_message),
    SERVER_ERROR_CODE(R.string.server_error_message),
    DEFAULT_RESPONSE_CODE(R.string.other_error_message),
    PAGING_DATA_LOADING_ERROR(R.string.data_loading_error)
}

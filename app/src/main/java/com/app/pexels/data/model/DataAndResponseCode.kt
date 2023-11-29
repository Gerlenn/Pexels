package com.app.pexels.data.model

data class DataAndResponseCode<T>(val dataFromServer: T, val code: Int)
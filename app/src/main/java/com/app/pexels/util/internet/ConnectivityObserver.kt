package com.app.pexels.util.internet

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<InternetConnectionStatus>
}
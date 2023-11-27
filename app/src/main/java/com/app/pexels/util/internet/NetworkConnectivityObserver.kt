package com.app.pexels.util.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkConnectivityObserver @Inject constructor(context: Context) : ConnectivityObserver {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<InternetConnectionStatus> = callbackFlow {

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch { send(InternetConnectionStatus.Available) }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch { send(InternetConnectionStatus.Lost) }
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)
        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }.distinctUntilChanged()
}

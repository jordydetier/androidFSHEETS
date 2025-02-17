package com.example.android.forzasheets.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * A Network manager.
 *
 * This class helps us know if the device is connected to the internet. This is used for the standings fragment
 */
class NetworkManager constructor(private var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() = with(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        ) {
            isConnectedToInternet()
        }

    private fun ConnectivityManager.isConnectedToInternet() =
        isConnected(getNetworkCapabilities(activeNetwork))

    private fun isConnected(networkCapabilities: NetworkCapabilities?): Boolean {
        return when (networkCapabilities) {
            null -> false
            else -> with(networkCapabilities) {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            }
        }
    }
}
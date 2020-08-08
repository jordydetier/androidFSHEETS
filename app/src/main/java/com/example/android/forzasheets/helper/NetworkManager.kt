package com.example.android.forzasheets.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkManager constructor(var applicationContext: Context) {
    val isConnectedToInternet: Boolean?
        get() = with(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        ) {
            isConnectedToInternet()
        }

    fun ConnectivityManager.isConnectedToInternet() = isConnected(getNetworkCapabilities(activeNetwork))

    fun isConnected(networkCapabilities: NetworkCapabilities?): Boolean {
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
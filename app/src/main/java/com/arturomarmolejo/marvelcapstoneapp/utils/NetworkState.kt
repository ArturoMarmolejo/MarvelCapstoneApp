package com.arturomarmolejo.marvelcapstoneapp.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkState @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    fun isInternetOn(): Boolean = connectivityManager
        .getNetworkCapabilities(connectivityManager.activeNetwork)
        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ?: false
}
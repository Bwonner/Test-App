package com.userposts.testapplication.feature

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class InternetObserver @Inject constructor(
    private val context: Context
) {
    private val listeners = mutableListOf<OnInternetStateListener>()

    var isOnline: Boolean = false
        private set

    private val broadcastReceiver = object : BroadcastReceiver() {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        override fun onReceive(context: Context, intent: Intent) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            isOnline = capabilities?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false

            for (i in listeners) {
                i.onInternetStateChanged(isOnline)
            }
        }
    }

    fun onCreate() {
        context.registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    fun onDestroy() {
        context.unregisterReceiver(broadcastReceiver)
    }

    fun setListener(listener: OnInternetStateListener) {
        listeners.add(listener)
    }

    interface OnInternetStateListener {
        fun onInternetStateChanged(isOnline: Boolean)
    }
}
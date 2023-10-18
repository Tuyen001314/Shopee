package com.example.shopeeapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkCheckerImpl(private val context: Context) : NetworkChecker {

    override fun isNetworkConnected(): Boolean {
        val connectionType = getConnectionType(context)
        return connectionType != 0
    }

    private fun getConnectionType(context: Context): Int {
        var result = 0
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            result = CONNECTION_WIFI
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            result = CONNECTION_CELLULAR
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                            result = CONNECTION_VPN
                        }
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> {
                            result = CONNECTION_WIFI
                        }
                        ConnectivityManager.TYPE_MOBILE -> {
                            result = CONNECTION_CELLULAR
                        }
                        ConnectivityManager.TYPE_VPN -> {
                            result = CONNECTION_VPN
                        }
                    }
                }

            }
        }
        return result
    }

    companion object {
        const val CONNECTION_CELLULAR = 1
        const val CONNECTION_WIFI = 2
        const val CONNECTION_VPN = 3
    }

}
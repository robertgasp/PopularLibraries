package com.example.popularlibraries.remote.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class NetworkStatus(context: Context) {

    private val networkSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    fun isOnline() = networkSubject.value?:false

    init{
        val request = NetworkRequest.Builder().build()

        connectivityManager?.registerNetworkCallback(request,object:ConnectivityManager.NetworkCallback(){

            /**Сеть есть*/
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            /**Сеть потеряна*/
            override fun onLost(network: Network) {
                networkSubject.onNext(false)
            }

            override fun onUnavailable() {
                networkSubject.onNext(false)
            }
        } )
    }
}
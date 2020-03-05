package com.yourgains.mvvmdaggerkotlintemplate.domain.repository

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.NetworkStateEvent

interface INetworkStateRepository {

    fun showNetworkConnectionError()
    fun showNetworkConnectionErrorDialog()
    fun hideNetworkConnectionError()
    fun observeNetworkConnectionState(): LiveData<NetworkStateEvent>
}
package com.yourgains.mvvmdaggerkotlintemplate.data.network

import android.content.Context
import android.content.Intent
import com.yourgains.mvvmdaggerkotlintemplate.common.BaseBroadcastReceiver
import com.yourgains.mvvmdaggerkotlintemplate.common.NetworkUtils
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.INetworkStateRepository
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
class NetworkChangeBroadcastReceiver : BaseBroadcastReceiver() {

    @Inject
    lateinit var networkStateRepository: INetworkStateRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        takeIf { NetworkUtils.isOnline() }?.let {
            networkStateRepository.hideNetworkConnectionError()
        } ?: let { networkStateRepository.showNetworkConnectionError() }
    }

}
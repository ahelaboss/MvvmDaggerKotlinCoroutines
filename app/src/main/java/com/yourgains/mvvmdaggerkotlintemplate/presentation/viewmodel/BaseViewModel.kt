package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yourgains.mvvmdaggerkotlintemplate.common.Navigate
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.NetworkStateEvent
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.network.ObserveNetworkStateUserCase

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
abstract class BaseViewModel(
    observeNetworkStateUserCase: ObserveNetworkStateUserCase? = null
) : ViewModel() {

    val navigationGlobalEvent = SingleLiveData<Navigate.Global>()
    val networkConnectionGlobalEvent = SingleLiveData<NetworkStateEvent>()

    private val networkConnectionObserve = Observer<NetworkStateEvent> {
        networkConnectionGlobalEvent.value = it
    }

    protected fun navigateTo(event: Navigate.Global) {
        navigationGlobalEvent.value = event
    }

    protected fun navigateUp() {
        navigationGlobalEvent.value = Navigate.Global.Back
    }

    open fun onResume() {
        //do nothing
    }

    open fun onPause() {

    }
}
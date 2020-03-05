package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel

import androidx.lifecycle.Observer
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.network.ObserveNetworkStateUserCase
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.progress.ObserveProgressUserCase

abstract class BaseProgressViewModel(
    observeProgressUserCase: ObserveProgressUserCase,
    observeNetworkStateUserCase: ObserveNetworkStateUserCase? = null
) : BaseViewModel(observeNetworkStateUserCase) {

    val progressGlobalEvent = SingleLiveData<StateEvent>()

    private val progressObserve = Observer<StateEvent> {
        progressGlobalEvent.value = it
    }

    init {
        observeProgressUserCase.apply { observe().observeForever(progressObserve) }
    }
}
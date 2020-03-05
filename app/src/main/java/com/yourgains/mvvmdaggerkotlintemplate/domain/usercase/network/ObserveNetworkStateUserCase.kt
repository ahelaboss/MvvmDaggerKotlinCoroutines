package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.network

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.NetworkStateEvent
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.INetworkStateRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseObserveUseCase
import javax.inject.Inject

class ObserveNetworkStateUserCase @Inject constructor(
    private val networkStateRepository: INetworkStateRepository
) : BaseObserveUseCase<NetworkStateEvent>() {

    override fun observe(): LiveData<NetworkStateEvent> =
        networkStateRepository.observeNetworkConnectionState()
}
package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.progress

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IProgressRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseObserveUseCase
import javax.inject.Inject

class ObserveProgressUserCase @Inject constructor(
    private val progressRepository: IProgressRepository
) : BaseObserveUseCase<StateEvent>() {

    override fun observe(): LiveData<StateEvent> = progressRepository.observeProgressState()
}
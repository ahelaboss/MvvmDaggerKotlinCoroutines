package com.yourgains.mvvmdaggerkotlintemplate.data.repository

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IProgressRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgressRepositoryImpl @Inject constructor() : IProgressRepository {

    private val progressStateEvent = SingleLiveData<StateEvent>()
    private val listProcess: MutableList<Any> = mutableListOf()

    init {
        progressStateEvent.value = StateEvent.HIDE
    }

    override fun showProgressBar(obj: Any) {
        listProcess.add(obj)
        takeIf { listProcess.isNotEmpty() }?.let {
            progressStateEvent.value = StateEvent.SHOW
        }
    }

    override fun hideProgressBar(obj: Any) {
        listProcess.remove(obj)
        takeIf { listProcess.isEmpty() }?.let {
            progressStateEvent.value = StateEvent.HIDE
        }
    }

    override fun observeProgressState(): LiveData<StateEvent> = progressStateEvent
}
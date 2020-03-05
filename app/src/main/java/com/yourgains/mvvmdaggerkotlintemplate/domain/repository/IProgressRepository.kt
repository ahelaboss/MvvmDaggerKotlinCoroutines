package com.yourgains.mvvmdaggerkotlintemplate.domain.repository

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent

interface IProgressRepository {

    fun showProgressBar(obj: Any)
    fun hideProgressBar(obj: Any)
    fun observeProgressState(): LiveData<StateEvent>
}
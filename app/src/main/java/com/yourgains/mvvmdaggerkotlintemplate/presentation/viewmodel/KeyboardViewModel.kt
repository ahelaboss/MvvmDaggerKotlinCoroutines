package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData
import com.yourgains.mvvmdaggerkotlintemplate.common.event.StateEvent
import javax.inject.Inject

class KeyboardViewModel @Inject constructor() : ViewModel() {

    val keyboardEvent: SingleLiveData<StateEvent> = SingleLiveData()

    fun showKeyboard() {
        keyboardEvent.value = StateEvent.SHOW
    }

    fun hideKeyboard() {
        keyboardEvent.value = StateEvent.HIDE
    }
}
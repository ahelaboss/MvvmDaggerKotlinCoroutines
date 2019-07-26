package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.yourgains.mvvmdaggerkotlintemplate.common.Navigate
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
abstract class BaseViewModel : ViewModel() {

    val navigationEvent = SingleLiveData<Navigate.Global>()

    protected fun navigateTo(event: Navigate.Global) {
        navigationEvent.value = event
    }
}
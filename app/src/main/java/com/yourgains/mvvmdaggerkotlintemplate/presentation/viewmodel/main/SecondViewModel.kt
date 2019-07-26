package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main

import com.yourgains.mvvmdaggerkotlintemplate.common.Navigate
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
class SecondViewModel @Inject constructor(): BaseViewModel() {

    fun onCloseClicked(){
        navigateTo(Navigate.Global.Close)
    }

}
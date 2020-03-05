package com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main

import androidx.lifecycle.Observer
import com.yourgains.mvvmdaggerkotlintemplate.common.Navigate
import com.yourgains.mvvmdaggerkotlintemplate.common.SingleLiveData
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.item.GetItemsUseCase
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.item.ObserveItemsUseCase
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
class FirstViewModel @Inject constructor(
    getItemsUseCase: GetItemsUseCase,
    observeItemsUseCase: ObserveItemsUseCase
): BaseViewModel() {

    val itemsLiveData = SingleLiveData<List<ItemUIModel>>()

    private val itemsObserver = Observer<List<ItemUIModel>> {
        itemsLiveData.value = it
    }

    init {
        getItemsUseCase.execute {
            onComplete {
                itemsLiveData.value = it
            }

            onNetworkError { Timber.e(it.toString()) }
            onError { Timber.e(it) }
        }

        observeItemsUseCase.observe().observeForever(itemsObserver)
    }

    fun onSecondPageClicked(){
        navigateTo(Navigate.Global.SecondPage)
    }
}
package com.yourgains.mvvmdaggerkotlintemplate.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.yourgains.mvvmdaggerkotlintemplate.di.mapkey.ViewModelKey
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
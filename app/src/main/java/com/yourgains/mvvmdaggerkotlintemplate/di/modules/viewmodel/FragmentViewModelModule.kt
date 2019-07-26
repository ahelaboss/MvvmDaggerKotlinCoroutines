package com.yourgains.mvvmdaggerkotlintemplate.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.yourgains.mvvmdaggerkotlintemplate.di.mapkey.ViewModelKey
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.FirstViewModel
import com.yourgains.mvvmdaggerkotlintemplate.presentation.viewmodel.main.SecondViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
@Module
abstract class FragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    abstract fun bindFirstViewModel(viewModel: FirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(viewModel: SecondViewModel): ViewModel
}
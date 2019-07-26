package com.yourgains.mvvmdaggerkotlintemplate.di.modules.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.yourgains.mvvmdaggerkotlintemplate.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
@Module(includes = [ActivityViewModelModule::class, FragmentViewModelModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
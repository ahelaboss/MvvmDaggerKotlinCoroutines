package com.yourgains.mvvmdaggerkotlintemplate.di.module

import com.yourgains.mvvmdaggerkotlintemplate.di.provider.MainActivityProviders
import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Alexey Shishov
 * on 26.07.19
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity
}
package com.yourgains.mvvmdaggerkotlintemplate.di.provider

import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main.fragment.FirstFragment
import com.yourgains.mvvmdaggerkotlintemplate.presentation.ui.main.fragment.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun provideSecondFragment(): SecondFragment
}
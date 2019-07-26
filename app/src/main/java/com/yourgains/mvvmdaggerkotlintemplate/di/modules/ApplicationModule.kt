package com.yourgains.mvvmdaggerkotlintemplate.di.modules

import android.app.Application
import android.content.Context
import com.yourgains.mvvmdaggerkotlintemplate.di.modules.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
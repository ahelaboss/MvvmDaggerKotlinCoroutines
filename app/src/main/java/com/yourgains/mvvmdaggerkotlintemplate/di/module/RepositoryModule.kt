package com.yourgains.mvvmdaggerkotlintemplate.di.module

import com.yourgains.mvvmdaggerkotlintemplate.data.repository.ItemsRepositoryImpl
import com.yourgains.mvvmdaggerkotlintemplate.data.repository.NetworkStateRepositoryImpl
import com.yourgains.mvvmdaggerkotlintemplate.data.repository.ProgressRepositoryImpl
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.INetworkStateRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IProgressRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsItemsRepository(repository: ItemsRepositoryImpl): IItemsRepository

    @Binds
    @Singleton
    abstract fun bindsProgressRepository(repository: ProgressRepositoryImpl): IProgressRepository

    @Binds
    @Singleton
    abstract fun bindsNetworkStateRepository(repository: NetworkStateRepositoryImpl): INetworkStateRepository
}
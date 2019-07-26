package com.yourgains.mvvmdaggerkotlintemplate.di.modules

import com.yourgains.mvvmdaggerkotlintemplate.data.repository.ItemsRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
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
    abstract fun bindsItemsRepository(repository: ItemsRepository): IItemsRepository
}
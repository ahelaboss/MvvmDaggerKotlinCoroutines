package com.yourgains.mvvmdaggerkotlintemplate.domain.repository

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.db.ItemDBModel

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
interface IItemsRepository {

    suspend fun get(): List<ItemDBModel>

    suspend fun delete(model: ItemDBModel)

    suspend fun clear()

    fun observe(): LiveData<List<ItemDBModel>>
}
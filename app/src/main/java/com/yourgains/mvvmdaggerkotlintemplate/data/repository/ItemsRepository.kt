package com.yourgains.mvvmdaggerkotlintemplate.data.repository

import androidx.lifecycle.LiveData
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.db.ItemDBModel
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.mappers.DbModelMapper
import com.yourgains.mvvmdaggerkotlintemplate.data.network.IApiRepository
import com.yourgains.mvvmdaggerkotlintemplate.data.storage.database.dao.IItemsDao
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
class ItemsRepository @Inject constructor(
    private val apiRepository: IApiRepository,
    private val itemsDao: IItemsDao
): IItemsRepository {

    override suspend fun get(): List<ItemDBModel> {
        val listItems = apiRepository.getListItems()
        itemsDao.insert(listItems.map { model -> DbModelMapper.map(model) })
        return itemsDao.select()
    }

    override suspend fun delete(model: ItemDBModel) {
        itemsDao.delete(model)
    }

    override suspend fun clear() {
        itemsDao.clear()
    }

    override fun observe(): LiveData<List<ItemDBModel>> = itemsDao.observe()
}
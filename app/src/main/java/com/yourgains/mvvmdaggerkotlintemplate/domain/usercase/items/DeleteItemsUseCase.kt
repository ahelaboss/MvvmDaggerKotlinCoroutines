package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.items

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.mappers.DbModelMapper
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseCoroutinesUseCase
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 25.07.19
 */
class DeleteItemsUseCase @Inject constructor(
    private val itemsRepository: IItemsRepository
) : BaseCoroutinesUseCase<Unit>() {

    private var model: ItemUIModel? = null

    override suspend fun executeOnBackground() {
        model?.let { itemsRepository.delete(DbModelMapper.map(it)) }
    }

    fun setData(model: ItemUIModel) {
        this.model = model
    }

}
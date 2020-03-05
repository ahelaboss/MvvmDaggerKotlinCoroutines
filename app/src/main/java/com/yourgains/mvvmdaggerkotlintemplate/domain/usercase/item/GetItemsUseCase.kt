package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.item

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.mapper.UiModelMapper
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseCoroutinesUseCase
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 25.07.19
 */
class GetItemsUseCase @Inject constructor(
    private val itemsRepository: IItemsRepository
) : BaseCoroutinesUseCase<List<ItemUIModel>>() {

    override suspend fun executeOnBackground(): List<ItemUIModel> =
        itemsRepository.get().map { model -> UiModelMapper.map(model) }
}
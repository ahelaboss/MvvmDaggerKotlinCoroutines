package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.mapper.UiModelMapper
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IItemsRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseObserveUseCase
import javax.inject.Inject

/**
 * Created by Alexey Shishov
 * on 25.07.19
 */
class ObserveItemsUseCase @Inject constructor(
    private val itemsRepository: IItemsRepository
) : BaseObserveUseCase<List<ItemUIModel>>() {

    override fun observe(): LiveData<List<ItemUIModel>> =
        Transformations.map(itemsRepository.observe()) { list -> list.map { UiModelMapper.map(it) } }

}
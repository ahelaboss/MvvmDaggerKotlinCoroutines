package com.yourgains.mvvmdaggerkotlintemplate.data.entity.mappers

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.db.ItemDBModel
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.network.ItemResponse
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
object UiModelMapper {

    fun map(model: ItemDBModel): ItemUIModel = ItemUIModel(model.id, model.name)
}
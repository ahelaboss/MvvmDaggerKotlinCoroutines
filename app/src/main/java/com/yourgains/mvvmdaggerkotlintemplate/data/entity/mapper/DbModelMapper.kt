package com.yourgains.mvvmdaggerkotlintemplate.data.entity.mapper

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.db.ItemDBModel
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.network.ItemResponse
import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.ItemUIModel

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
object DbModelMapper {

    fun map(model: ItemResponse): ItemDBModel = ItemDBModel(model.id, model.name)

    fun map(model: ItemUIModel): ItemDBModel = ItemDBModel(model.id, model.name)
}
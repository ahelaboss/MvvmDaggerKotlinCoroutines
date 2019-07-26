package com.yourgains.mvvmdaggerkotlintemplate.data.network

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.network.ItemResponse

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
interface IApiRepository {

    suspend fun getListItems(): List<ItemResponse>
}
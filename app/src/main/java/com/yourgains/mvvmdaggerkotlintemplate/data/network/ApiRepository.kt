package com.yourgains.mvvmdaggerkotlintemplate.data.network

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.network.ItemResponse
import com.yourgains.mvvmdaggerkotlintemplate.data.network.services.ApiServices

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */
class ApiRepository(private val apiServices: ApiServices): IApiRepository {

    override suspend fun getListItems(): List<ItemResponse> = apiServices.getListAsync().await()
}
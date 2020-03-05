package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.block

import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.BaseCoroutinesUseCase

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */

typealias CompletionBlock<T> = BaseCoroutinesUseCase.Request<T>.() -> Unit
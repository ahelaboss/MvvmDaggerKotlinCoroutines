package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase

import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.IProgressRepository
import javax.inject.Inject

abstract class BaseProgressCoroutinesUseCase<T> : BaseCoroutinesUseCase<T>() {

    @Inject
    lateinit var progressRepository: IProgressRepository

    override fun executePreBackground() {
        progressRepository.showProgressBar(this)
    }

    override fun executePostBackground() {
        progressRepository.hideProgressBar(this)
    }
}
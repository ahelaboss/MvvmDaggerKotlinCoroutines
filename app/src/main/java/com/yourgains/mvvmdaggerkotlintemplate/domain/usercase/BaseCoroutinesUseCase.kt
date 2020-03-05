package com.yourgains.mvvmdaggerkotlintemplate.domain.usercase

import com.yourgains.mvvmdaggerkotlintemplate.data.entity.presentation.NetworkErrorUiModel
import com.yourgains.mvvmdaggerkotlintemplate.domain.repository.INetworkStateRepository
import com.yourgains.mvvmdaggerkotlintemplate.domain.usercase.block.CompletionBlock
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Alexey Shishov
 * on 24.07.19
 */

abstract class BaseCoroutinesUseCase<T> {

    @Inject
    lateinit var networkStateRepository: INetworkStateRepository

    private var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main

    protected var userId: String? = null
    private var delay: Long = 0L

    fun setDelay(delay: Long) {
        this.delay = delay
    }

    protected open suspend fun executeOnBackgroundPre() {
        //do nothing
    }

    protected open fun executePreBackground() {
        //do nothing
    }

    protected open fun executePostBackground() {
        //do nothing
    }

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch { launchScope(response) }
    }

    private suspend fun launchScope(response: Request<T>, isRetry: Boolean = true) {
        if (delay > 0) delay(delay)
        executePreBackground()
        try {
            val result = withContext(backgroundContext) {
                executeOnBackgroundPre()
                executeOnBackground()
            }
            response(result)
        } catch (ex: CancellationException) {
            Timber.d(ex)
            response(ex)
        } catch (ex: ConnectException) {
            Timber.e(ex)
            networkStateRepository.showNetworkConnectionErrorDialog()
            response(NetworkErrorUiModel(0, ex.message))
        } catch (ex: UnknownHostException) {
            Timber.e(ex)
            networkStateRepository.showNetworkConnectionErrorDialog()
            response(NetworkErrorUiModel(0, ex.message))
        } catch (ex: HttpException) {
            val error = getError(ex)
            Timber.e(error.toString())
            response(error)
        } catch (ex: Exception) {
            Timber.e(ex)
            response(ex)
        } finally {
            executePostBackground()
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    private fun getError(ex: HttpException): NetworkErrorUiModel =
        NetworkErrorUiModel(ex.code(), ex.message())

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onNetworkError: ((NetworkErrorUiModel) -> Unit)? = null
        private var onError: ((Exception) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onNetworkError(block: (NetworkErrorUiModel) -> Unit) {
            onNetworkError = block
        }

        fun onError(block: (Exception) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: NetworkErrorUiModel) {
            onNetworkError?.invoke(error)
        }

        operator fun invoke(error: Exception) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }

    }
}
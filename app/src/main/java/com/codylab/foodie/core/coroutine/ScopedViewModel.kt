package com.codylab.foodie.core.coroutine

import com.codylab.foodie.core.reactive.RxViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class ScopedViewModel : RxViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val job = Job()

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}
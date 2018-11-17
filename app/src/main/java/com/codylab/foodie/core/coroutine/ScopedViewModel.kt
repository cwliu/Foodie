package com.codylab.foodie.core.coroutine

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class ScopedViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val job = Job()

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}
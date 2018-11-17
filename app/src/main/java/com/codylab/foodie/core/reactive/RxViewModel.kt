package com.codylab.foodie.core.reactive

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel: ViewModel() {
    val disposables = CompositeDisposable()

    protected fun <T : Disposable> T.addSubscription(): T {
        disposables.add(this)
        return this
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
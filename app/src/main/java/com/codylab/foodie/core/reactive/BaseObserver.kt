package com.codylab.foodie.core.reactive

import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T>: DisposableObserver<T>() {
    override fun onStart() {
        super.onStart()
    }
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }
}
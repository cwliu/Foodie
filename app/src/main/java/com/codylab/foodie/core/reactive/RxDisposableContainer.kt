package com.codylab.foodie.core.reactive

import io.reactivex.disposables.CompositeDisposable

interface RxDisposableContainer {

    val disposables: CompositeDisposable
        get() {
            return CompositeDisposable()
        }
}
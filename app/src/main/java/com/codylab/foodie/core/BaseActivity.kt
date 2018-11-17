package com.codylab.foodie.core

import com.codylab.foodie.core.reactive.RxDisposableContainer
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity(), RxDisposableContainer {

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }
}
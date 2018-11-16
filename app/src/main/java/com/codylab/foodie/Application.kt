package com.codylab.foodie

import com.codylab.foodie.dagger.CoreModule
import com.codylab.foodie.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class Application : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().coreModule(CoreModule(this)).build()
    }
}
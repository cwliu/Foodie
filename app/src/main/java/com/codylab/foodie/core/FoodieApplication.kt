package com.codylab.foodie.core

import com.codylab.foodie.core.dagger.CoreModule
import com.codylab.foodie.core.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FoodieApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().coreModule(CoreModule(this)).build()
    }
}
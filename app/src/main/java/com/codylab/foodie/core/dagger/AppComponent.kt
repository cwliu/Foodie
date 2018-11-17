package com.codylab.foodie.core.dagger


import android.app.Application
import com.codylab.finefood.core.dagger.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        CoreModule::class,
        LocationModule::class,
        ZomatoModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: Application)
}
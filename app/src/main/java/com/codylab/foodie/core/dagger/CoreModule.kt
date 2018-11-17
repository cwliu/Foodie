package com.codylab.foodie.core.dagger

import android.content.Context
import com.codylab.foodie.core.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }
}
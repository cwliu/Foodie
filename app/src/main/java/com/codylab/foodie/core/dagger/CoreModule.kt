package com.codylab.foodie.core.dagger

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.codylab.foodie.core.FoodieApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(private val foodieApplication: FoodieApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return foodieApplication
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return foodieApplication.applicationContext
    }

    @Singleton
    @Provides
    fun provideResource(): Resources {
        return foodieApplication.resources
    }
}
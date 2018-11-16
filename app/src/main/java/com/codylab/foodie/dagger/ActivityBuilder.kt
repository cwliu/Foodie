package com.codylab.foodie.dagger

import com.codylab.foodie.feature.RestaurantListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): RestaurantListActivity
}
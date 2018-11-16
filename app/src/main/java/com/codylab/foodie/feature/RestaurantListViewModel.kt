package com.codylab.foodie.feature

import android.arch.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(): ViewModel() {

    fun onLoaded() {
        Timber.d("Hi")
    }
}
package com.codylab.foodie.feature

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.codylab.foodie.R
import com.codylab.foodie.extension.getViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RestaurantListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        viewModel = getViewModel(viewModelFactory)
        viewModel.onLoaded()
    }
}

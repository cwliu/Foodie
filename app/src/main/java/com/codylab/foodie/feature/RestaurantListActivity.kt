package com.codylab.foodie.feature

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.codylab.foodie.R
import com.codylab.foodie.core.BaseActivity
import com.codylab.foodie.core.extension.getViewModel
import com.codylab.foodie.core.extension.nonNull
import com.codylab.foodie.core.extension.requestLocationPermission
import com.codylab.foodie.core.extension.showError
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class RestaurantListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        viewModel = getViewModel(viewModelFactory)

        viewModel.uiModel.nonNull().observe(this, Observer<RestaurantListUiModel> { uiModel ->
            uiModel?.message?.getDataIfNotHandled()?.let {
                showError(it)
            }
        })
    }

    override fun onStart() {
        super.onStart()

        disposables += requestLocationPermission().subscribe({ granted ->
            viewModel.onLocationRequested(granted)
        }, {
            showError(it)
        })
    }
}

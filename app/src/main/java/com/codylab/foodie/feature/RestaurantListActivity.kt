package com.codylab.foodie.feature

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.core.view.isVisible
import com.codylab.foodie.R
import com.codylab.foodie.core.BaseActivity
import com.codylab.foodie.core.extension.*
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import javax.inject.Inject

class RestaurantListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        viewModel = getViewModel(viewModelFactory)

        viewModel.uiModel.nonNull().observeNonNull(this) { uiModel ->
            uiModel?.message?.getDataIfNotHandled()?.let {
                showError(it)
            }

            uiModel?.zomatoRestaurantList?.let {
                showToast(it.toString())
            }

            uiModel.isLoading.let {
                progressBar.isVisible = it
            }
        }
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

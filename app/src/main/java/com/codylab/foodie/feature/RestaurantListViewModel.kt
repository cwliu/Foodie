package com.codylab.foodie.feature

import android.content.res.Resources
import com.codylab.foodie.R
import com.codylab.foodie.core.coroutine.ScopedViewModel
import com.codylab.foodie.core.extension.NonNullMediatorLiveData
import com.codylab.foodie.core.extension.applySchedulers
import com.codylab.foodie.core.livedata.Event
import com.codylab.foodie.core.reactive.BaseObserver
import com.codylab.foodie.core.zomato.model.search.Restaurant
import com.codylab.foodie.usecase.GetZomatoRestaurantUseCase
import io.reactivex.rxkotlin.plusAssign
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject
import javax.inject.Singleton

data class RestaurantListUiModel(
    var zomatoRestaurantList: List<Restaurant>? = null,
    var isLoading: Boolean = false,
    var message: Event<String>? = null
)

@Singleton
class RestaurantListViewModel @Inject constructor(
    private val resources: Resources,
    private val getZomatoRestaurantUseCase: GetZomatoRestaurantUseCase
) : ScopedViewModel() {

    val uiModel = NonNullMediatorLiveData<RestaurantListUiModel>()

    private val uiModelData = RestaurantListUiModel()

    fun onLocationRequested(grant: Boolean) {
        if (!grant) {
            uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
            uiModel.postValue(uiModelData)
            return
        }

        disposables += getZomatoRestaurantUseCase()
            .applySchedulers()
            .subscribeWith(object : BaseObserver<List<Restaurant>>() {
                override fun onStart() {
                    super.onStart()

                    uiModelData.isLoading = true
                    uiModel.postValue(uiModelData)
                }

                override fun onNext(restaurants: List<Restaurant>) {
                    uiModelData.isLoading = false
                    uiModelData.zomatoRestaurantList = restaurants
                    uiModel.postValue(uiModelData)
                }

                override fun onComplete() {
                    super.onComplete()
                    uiModelData.isLoading = false
                    uiModel.postValue(uiModelData)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    uiModelData.isLoading = false
                    uiModelData.message = Event(e.message.toString())
                    uiModel.postValue(uiModelData)
                }
            })
    }

    @TestOnly
    fun showNoPermissionError() {
        uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
        uiModel.postValue(uiModelData)
    }
}
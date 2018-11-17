package com.codylab.foodie.feature

import android.content.res.Resources
import com.codylab.finefood.core.livedata.Event
import com.codylab.finefood.core.model.Location
import com.codylab.foodie.R
import com.codylab.foodie.core.coroutine.ScopedViewModel
import com.codylab.foodie.core.extension.NonNullMediatorLiveData
import com.codylab.foodie.core.reactive.BaseObserver
import com.codylab.foodie.usecase.GetUserLocationUseCase
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject
import javax.inject.Singleton

data class RestaurantListUiModel(
    var isLoading: Boolean = false,
    var message: Event<String>? = null
)

@Singleton
class RestaurantListViewModel @Inject constructor(
    private val resources: Resources,
    private val getUserLocation: GetUserLocationUseCase
) : ScopedViewModel() {

    val uiModel = NonNullMediatorLiveData<RestaurantListUiModel>()

    private val uiModelData = RestaurantListUiModel()

    fun onLocationRequested(grant: Boolean) {
        if (!grant) {
            uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
            uiModel.postValue(uiModelData)
            return
        }

        getUserLocation().subscribeWith(object: BaseObserver<Location>(){
            override fun onNext(location: Location) {
                uiModelData.message = Event(location.toString())
                uiModel.postValue(uiModelData)
            }
        }).addSubscription()
    }

    @TestOnly
    fun showNoPermissionError() {
        uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
        uiModel.postValue(uiModelData)
    }
}
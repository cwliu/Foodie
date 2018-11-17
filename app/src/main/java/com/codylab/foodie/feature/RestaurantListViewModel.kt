package com.codylab.foodie.feature

import android.content.res.Resources
import com.codylab.finefood.core.livedata.Event
import com.codylab.foodie.R
import com.codylab.foodie.core.coroutine.Response
import com.codylab.foodie.core.coroutine.ScopedViewModel
import com.codylab.foodie.core.coroutine.safeCall
import com.codylab.foodie.core.extension.NonNullMediatorLiveData
import com.codylab.foodie.core.extension.exhaustive
import com.codylab.foodie.core.repository.UserLocationRepository
import kotlinx.coroutines.launch
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
    private val userLocationRepository: UserLocationRepository
) : ScopedViewModel() {

    val uiModel = NonNullMediatorLiveData<RestaurantListUiModel>()

    private val uiModelData = RestaurantListUiModel()

    fun onLocationRequested(grant: Boolean) {
        if (!grant) {
            uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
            uiModel.postValue(uiModelData)
            return
        }

        launch {
            uiModelData.isLoading = true
            uiModel.postValue(uiModelData)

            val locationResponse = safeCall(resources.getString(R.string.error_failed_to_get_user_location)) {
                Response.Success(userLocationRepository.getLastKnownLocation())
            }

            when(locationResponse) {
                is Response.Success -> {
                    uiModelData.message = Event(locationResponse.data.toString())
                    uiModel.postValue(uiModelData)
                }
                is Response.Error -> {
                    uiModelData.message = Event(locationResponse.exception.toString())
                    uiModel.postValue(uiModelData)

                }
            }.exhaustive

            uiModelData.isLoading = false
            uiModel.postValue(uiModelData)
        }
    }

    @TestOnly
    fun showNoPermissionError() {
        uiModelData.message = Event(resources.getString(R.string.error_no_location_permission))
        uiModel.postValue(uiModelData)
    }
}
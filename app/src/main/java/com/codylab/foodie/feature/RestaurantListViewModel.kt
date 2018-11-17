package com.codylab.foodie.feature

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.codylab.finefood.core.livedata.Event
import com.codylab.foodie.R
import com.codylab.foodie.core.coroutine.ScopedViewModel
import com.codylab.foodie.core.extension.NonNullMediatorLiveData
import com.codylab.foodie.core.extension.nonNull
import com.codylab.foodie.core.repository.LastKnownLocationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

data class RestaurantListUiModel(
    var isLoading: Boolean = false,
    var message: Event<String>? = null
)

@Singleton
class RestaurantListViewModel @Inject constructor(
    private val resources: Resources,
    private val lastKnownLocationRepository: LastKnownLocationRepository
) : ScopedViewModel() {

    val uiModel: NonNullMediatorLiveData<RestaurantListUiModel>
        get() = _uiModel.nonNull()

    private val _uiModel = MutableLiveData<RestaurantListUiModel>()
    private val uiModelData = RestaurantListUiModel()

    fun onLocationRequested(grant: Boolean) {
        if (!grant) {
            uiModelData.message = Event(resources.getString(R.string.failed_to_get_user_location))
            _uiModel.postValue(uiModelData)
            return
        }

        launch {
            val location = lastKnownLocationRepository.getLastLocationCoroutine()
            location?.let {
                uiModelData.message = Event(it.toString())
                _uiModel.postValue(uiModelData)
            }
        }
    }
}
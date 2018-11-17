package com.codylab.foodie.feature

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.codylab.finefood.core.livedata.Event
import com.codylab.foodie.Application
import com.codylab.foodie.R
import com.codylab.foodie.core.extension.NonNullMediatorLiveData
import com.codylab.foodie.core.extension.nonNull
import javax.inject.Inject

data class RestaurantListUiModel(
    var isLoading: Boolean = false,
    var errorMessage: Event<String>? = null
)

class RestaurantListViewModel @Inject constructor(val application: Application) : AndroidViewModel(application) {

    val uiModel: NonNullMediatorLiveData<RestaurantListUiModel>
        get() = _uiModel.nonNull()

    private val _uiModel = MutableLiveData<RestaurantListUiModel>()
    private val uiModelData = RestaurantListUiModel()

    fun onLocationRequested(grant: Boolean) {
        if (!grant) {
            uiModelData.errorMessage = Event(application.resources.getString(R.string.failed_to_get_user_location))
            _uiModel.postValue(uiModelData)
        }
    }
}
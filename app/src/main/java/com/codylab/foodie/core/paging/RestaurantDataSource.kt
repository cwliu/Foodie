package com.codylab.foodie.core.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant

class RestaurantDataSource(
    private val restaurantRepository: ZomatoRestaurantRepository,
    private val location: Location
) : PositionalDataSource<Restaurant>() {

    // TODO Observe this network state
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Restaurant>) {
        networkState.postValue(NetworkState.LOADING)

        try {
            val response = restaurantRepository.getRestaurantsResponse(location, params.requestedStartPosition, params.pageSize).blockingGet()
            networkState.postValue(NetworkState.LOADED)
            val restaurants = response.restaurants.map { it.restaurant }
            callback.onResult(restaurants, response.results_start)
        } catch (e: NoSuchElementException) {
            networkState.postValue(NetworkState.error(e.message ?: "unknown err"))
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Restaurant>) {
        networkState.postValue(NetworkState.LOADING)
        try {
            val response = restaurantRepository.getRestaurantsResponse(location, params.startPosition, params.loadSize).blockingGet()
            networkState.postValue(NetworkState.LOADED)
            val restaurants = response.restaurants.map { it.restaurant }
            callback.onResult(restaurants)
        } catch (e: NoSuchElementException) {
            networkState.postValue(NetworkState.error(e.message ?: "unknown err"))
        }
    }
}
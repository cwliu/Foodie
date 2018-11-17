package com.codylab.foodie.core.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant

class RestaurantDataSourceFactory(
    private val restaurantRepository: ZomatoRestaurantRepository,
    private val location: Location
): DataSource.Factory<Int, Restaurant>() {

    private val dataSourceLiveData = MutableLiveData<RestaurantDataSource>()

    override fun create(): DataSource<Int, Restaurant> {
        val source = RestaurantDataSource(restaurantRepository, location)
        dataSourceLiveData.postValue(source)
        return source
    }
}
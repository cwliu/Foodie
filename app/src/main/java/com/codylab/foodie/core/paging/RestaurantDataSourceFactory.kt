package com.codylab.foodie.core.paging

import android.arch.paging.DataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant

class RestaurantDataSourceFactory(
    private val restaurantRepository: ZomatoRestaurantRepository,
    val location: Location
) : DataSource.Factory<Int, Restaurant>() {

    override fun create(): DataSource<Int, Restaurant> {
        return RestaurantDataSource(restaurantRepository, location)
    }
}
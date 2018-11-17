package com.codylab.foodie.core.repository

import com.codylab.foodie.core.extension.applySchedulers
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.zomato.ZomatoApi
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZomatoRestaurantRepository @Inject constructor(
    private val zomatoApi: ZomatoApi
) {
    fun getRestaurants(location: Location): Single<List<Restaurant>> {
        return zomatoApi.search(location.latitude, location.longitude).map { response ->
            response.restaurants.map { it.restaurant }
        }.applySchedulers()
    }
}
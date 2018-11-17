package com.codylab.foodie.core.repository

import com.codylab.finefood.core.model.Location
import com.codylab.finefood.core.zomato.ZomatoApi
import com.codylab.finefood.core.zomato.model.search.SearchRestaurant
import com.codylab.foodie.core.extension.applySchedulers
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZomatoRestaurantRepository @Inject constructor(
    private val zomatoApi: ZomatoApi
) {
    fun getRestaurants(location: Location): Single<List<SearchRestaurant>> {
        return zomatoApi.search(location.latitude, location.longitude).map { response ->
            response.restaurants
        }.applySchedulers()
    }
}
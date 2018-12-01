package com.codylab.foodie.core.repository

import com.codylab.foodie.core.extension.applySchedulers
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.zomato.ZomatoApi
import com.codylab.foodie.core.zomato.model.search.SearchResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZomatoApiRepository @Inject constructor(
    private val zomatoApi: ZomatoApi
) {
    fun getRestaurantsResponse(location: Location, start: Int, count: Int): Single<SearchResponse> {
        return zomatoApi.search(location.latitude, location.longitude, start, count).applySchedulers()
    }
}
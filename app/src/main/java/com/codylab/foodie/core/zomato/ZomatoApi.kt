package com.codylab.finefood.core.zomato

import com.codylab.foodie.core.zomato.model.GeocodeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoApi {

    @GET("geocode")
    fun geocode(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<GeocodeResponse>
}
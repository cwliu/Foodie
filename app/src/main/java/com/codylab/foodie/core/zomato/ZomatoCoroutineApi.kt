package com.codylab.finefood.core.zomato

import com.codylab.foodie.core.zomato.model.GeocodeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoCoroutineApi {

    @GET("search")
    fun search(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cuisines") cuisines: String = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20"
    ): Deferred<GeocodeResponse>
}
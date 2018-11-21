package com.codylab.foodie.core.paging

import android.arch.paging.DataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.UserLocationRepository
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.room.AppDatabase
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.subjects.BehaviorSubject

class RestaurantDataSourceFactory(
    private val appDatabase: AppDatabase,
    private val restaurantRepository: ZomatoRestaurantRepository,
    private val userLocationRepository: UserLocationRepository,
    private val initialLocation: Location
) : DataSource.Factory<Int, Restaurant>() {

    var networkStateObservable = BehaviorSubject.create<NetworkState>()

    override fun create(): DataSource<Int, Restaurant> {
        val dataSource = RestaurantDataSource(appDatabase, restaurantRepository, userLocationRepository, initialLocation)

        dataSource.networkStateSubject.subscribe(networkStateObservable)

        return dataSource
    }
}
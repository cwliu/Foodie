package com.codylab.foodie.core.paging

import android.arch.paging.DataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.UserLocationRepository
import com.codylab.foodie.core.repository.ZomatoApiRepository
import com.codylab.foodie.core.room.AppDatabase
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.subjects.BehaviorSubject

class RestaurantDataSourceFactory(
    private val appDatabase: AppDatabase,
    private val apiRepository: ZomatoApiRepository,
    private val userLocationRepository: UserLocationRepository,
    private val initialLocation: Location
) : DataSource.Factory<Int, Restaurant>() {

    var networkStateSubject = BehaviorSubject.create<NetworkState>()

    override fun create(): DataSource<Int, Restaurant> {
        return RestaurantDataSource(appDatabase, apiRepository, userLocationRepository, initialLocation).apply {
            this.networkStateSubject.subscribe(this@RestaurantDataSourceFactory.networkStateSubject)
        }
    }
}
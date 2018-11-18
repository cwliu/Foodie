package com.codylab.foodie.core.paging

import android.arch.paging.DataSource
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.repository.UserLocationRepository
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.subjects.BehaviorSubject

class RestaurantDataSourceFactory(
    private val restaurantRepository: ZomatoRestaurantRepository,
    private val userLocationRepository: UserLocationRepository,
    private val initialLocation: Location
) : DataSource.Factory<Int, Restaurant>() {

    var networkStateObservable = BehaviorSubject.create<NetworkState>()

    override fun create(): DataSource<Int, Restaurant> {
        val dataSource = RestaurantDataSource(restaurantRepository, userLocationRepository, initialLocation)

        val subject1 = dataSource.networkStateSubject
        val subject2 = networkStateObservable
        val dispoable = subject1.subscribe(
            { subject2.onNext(it) },
            { subject2.onError(it) },
            { subject2.onComplete() },
            {
                subject2.onSubscribe(it)
            }
        )

        return dataSource
    }
}
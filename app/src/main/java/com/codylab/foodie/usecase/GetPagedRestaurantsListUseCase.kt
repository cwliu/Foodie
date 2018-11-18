package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.paging.NetworkState
import com.codylab.foodie.core.paging.RestaurantDataSourceFactory
import com.codylab.foodie.core.repository.UserLocationRepository
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetPagedRestaurantsListUseCase @Inject constructor(
    private val zomatoRestaurantRepository: ZomatoRestaurantRepository,
    private val userLocationRepository: UserLocationRepository
) {

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 20
        const val ENABLE_PLACEHOLDERS = false
    }

    operator fun invoke(initialLocation: Location): Observable<Pair<PagedList<Restaurant>, NetworkState>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS).build()

        val factory = RestaurantDataSourceFactory(zomatoRestaurantRepository, userLocationRepository, initialLocation)
        val networkStateObservable = factory.networkStateObservable

        return Observable.combineLatest(
            RxPagedListBuilder(factory, config).buildObservable(),
            networkStateObservable,
            BiFunction { pagedList, networkState ->
                Pair(pagedList, networkState)
            })
    }
}
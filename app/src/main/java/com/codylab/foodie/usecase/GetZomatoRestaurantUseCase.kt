package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.codylab.foodie.core.paging.RestaurantDataSourceFactory
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class GetZomatoRestaurantUseCase @Inject constructor(
    val userLocationUseCase: GetUserLocationUseCase,
    private val zomatoRestaurantRepository: ZomatoRestaurantRepository
) {
    operator fun invoke(): Observable<PagedList<Restaurant>> {

        val config = PagedList.Config.Builder().setPageSize(20).setPrefetchDistance(20).setEnablePlaceholders(false).build()

        return userLocationUseCase().firstElement().toObservable().flatMap { location ->
            val searchDataSourceFactory = RestaurantDataSourceFactory(zomatoRestaurantRepository, location)
            RxPagedListBuilder(searchDataSourceFactory, config).buildObservable()
        }
    }
}
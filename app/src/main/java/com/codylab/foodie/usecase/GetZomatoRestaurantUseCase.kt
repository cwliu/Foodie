package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import com.codylab.foodie.core.paging.NetworkState
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class GetZomatoRestaurantUseCase @Inject constructor(
    val userLocationUseCase: GetUserLocationUseCase,
    val getPagedRestaurantsListUseCase: GetPagedRestaurantsListUseCase
) {
    operator fun invoke(): Observable<Pair<PagedList<Restaurant>, NetworkState>> {
        return userLocationUseCase().firstElement().toObservable().flatMap { location ->
            getPagedRestaurantsListUseCase(location)
        }
    }
}
package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.codylab.foodie.core.model.Location
import com.codylab.foodie.core.paging.RestaurantDataSourceFactory
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.zomato.model.search.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class GetPagedRestaurantsListUseCase @Inject constructor(
    private val zomatoRestaurantRepository: ZomatoRestaurantRepository
) {

    companion object {
        val PAGE_SIZE = 20
        val PREFETCH_DISTANCE = 20
        val ENABLE_PLACEHOLDERS = false
    }

    operator fun invoke(location: Location): Observable<PagedList<Restaurant>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS).build()

        val factory = RestaurantDataSourceFactory(zomatoRestaurantRepository, location)
        return RxPagedListBuilder(factory, config).buildObservable()
    }
}
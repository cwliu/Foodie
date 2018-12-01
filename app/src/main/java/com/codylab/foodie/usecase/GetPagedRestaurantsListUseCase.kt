package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.codylab.foodie.core.paging.NetworkState
import com.codylab.foodie.core.paging.RestaurantBoundaryCallBack
import com.codylab.foodie.core.room.AppDatabase
import com.codylab.foodie.core.room.RestaurantEntity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPagedRestaurantsListUseCase @Inject constructor(
    private val appDatabase: AppDatabase,
    private val restaurantBoundaryCallBack: RestaurantBoundaryCallBack
) {

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 20
        const val ENABLE_PLACEHOLDERS = false
    }

    operator fun invoke(): Observable<Pair<PagedList<RestaurantEntity>, NetworkState>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS).build()

        restaurantBoundaryCallBack.dispose()

        val dbFactory = appDatabase.restaurantDao().getAll()

        val pagedListObservable = RxPagedListBuilder(dbFactory, config)
            .setBoundaryCallback(restaurantBoundaryCallBack)
            .buildObservable()

        return Observable.combineLatest(
            pagedListObservable,
            restaurantBoundaryCallBack.networkStateObservable,
            BiFunction { pagedList, networkState ->
                Pair(pagedList, networkState)
            })
    }
}
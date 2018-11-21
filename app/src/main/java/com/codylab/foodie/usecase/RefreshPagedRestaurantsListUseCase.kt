package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import com.codylab.foodie.core.paging.NetworkState
import com.codylab.foodie.core.room.AppDatabase
import com.codylab.foodie.core.room.RestaurantEntity
import io.reactivex.Observable
import io.reactivex.rxkotlin.toCompletable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RefreshPagedRestaurantsListUseCase @Inject constructor(
    private val appDatabase: AppDatabase,
    private val getPagedRestaurantsListUseCase: GetPagedRestaurantsListUseCase
) {

    operator fun invoke(): Observable<Pair<PagedList<RestaurantEntity>, NetworkState>> {
        return {
            appDatabase.restaurantDao().deleteAll()
        }.toCompletable().subscribeOn(Schedulers.io()).andThen(
            getPagedRestaurantsListUseCase()
        )
    }
}

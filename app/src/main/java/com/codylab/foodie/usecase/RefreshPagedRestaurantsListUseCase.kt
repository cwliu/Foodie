package com.codylab.foodie.usecase

import android.arch.paging.PagedList
import com.codylab.foodie.core.paging.NetworkState
import com.codylab.foodie.core.room.RestaurantEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RefreshPagedRestaurantsListUseCase @Inject constructor(
    private val deleteDatabase: DeleteDatabaseUseCase,
    private val getPagedRestaurantsList: GetPagedRestaurantsListUseCase
) {

    operator fun invoke(): Observable<Pair<PagedList<RestaurantEntity>, NetworkState>> {
        return deleteDatabase().andThen(getPagedRestaurantsList())
    }
}

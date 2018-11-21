package com.codylab.foodie.core.paging

import android.arch.paging.PagedList
import android.content.Context
import android.support.annotation.MainThread
import com.codylab.foodie.core.repository.UserLocationRepository
import com.codylab.foodie.core.repository.ZomatoRestaurantRepository
import com.codylab.foodie.core.room.AppDatabase
import com.codylab.foodie.core.room.RestaurantEntity
import com.codylab.foodie.core.room.toRestaurantEntity
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestaurantBoundaryCallBack @Inject constructor(
    private val context: Context,
    private val appDatabase: AppDatabase,
    private val restaurantRepository: ZomatoRestaurantRepository,
    private val userLocationRepository: UserLocationRepository

) : PagedList.BoundaryCallback<RestaurantEntity>() {

    var totalCount = 0
    var lastCount = 0

    var isLoading = AtomicBoolean()

    val networkStateObservable = BehaviorSubject.create<NetworkState>()

    private var disposable: Disposable? = null

    companion object {
        val PAGE_SIZE = 20
    }

    init {
        networkStateObservable.onNext(NetworkState.LOADED)
    }

    @MainThread
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        if (isLoading.get()) {
            return
        }
        downloadRestaurants()
    }

    private fun downloadRestaurants() {
        totalCount = 0
        lastCount = 0

        isLoading.set(true)

        networkStateObservable.onNext(NetworkState.LOADING)

        disposable = userLocationRepository.getLocationUpdates().firstOrError()
            .observeOn(Schedulers.io()).flatMap {
                restaurantRepository.getRestaurantsResponse(it, totalCount, PAGE_SIZE)
            }.observeOn(Schedulers.io()).doOnSuccess { searchResponse ->
                lastCount = searchResponse.restaurants.size
                totalCount += lastCount

                appDatabase.restaurantDao()
                    .insert(*(searchResponse.restaurants.map { it.restaurant.toRestaurantEntity() }.toTypedArray()))
                networkStateObservable.onNext(NetworkState.LOADED)

            }.repeatUntil {
                lastCount == 0
            }.subscribe({
                isLoading.set(false)
            }, {
                networkStateObservable.onNext(NetworkState.error(it.toString()))
                isLoading.set(false)
            })
    }

    fun dispose() {
        disposable?.dispose()
    }
}